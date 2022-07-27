package com.test.nettyboot.httpserver.configuration;

import com.test.nettyboot.httpserver.netty.iohandler.FilterLogginglHandler;
import com.test.nettyboot.httpserver.netty.iohandler.HttpServerHandler;
import com.test.nettyboot.httpserver.netty.iohandler.InterceptorHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;

// 基于Netty开发的http服务器
@Configuration
public class NettyHttpServer implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyHttpServer.class);

    @Value("${server.port}")
    private int port;

    @Resource
    private InterceptorHandler interceptorHandler;

    @Resource
    private HttpServerHandler httpServerHandler;

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {

        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        applicationContext.addApplicationListener(future->{
            System.out.println("-------------------");
        });

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(NioChannelOption.TCP_NODELAY, true)
                .childOption(NioChannelOption.SO_REUSEADDR, true)
                .childOption(NioChannelOption.SO_KEEPALIVE, false)
                .childOption(NioChannelOption.SO_RCVBUF, 2048)
                .childOption(NioChannelOption.SO_SNDBUF, 2048)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast("codec", new HttpServerCodec());
                        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
                        ch.pipeline().addLast("logging", new FilterLogginglHandler());
                        ch.pipeline().addLast("interceptor", interceptorHandler);
                        ch.pipeline().addLast("bizHandler", httpServerHandler);
                    }
                });
        ChannelFuture cf = bootstrap.bind(port).syncUninterruptibly().addListener(future -> {
            String logBanner = "\n\n" +
                    "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                    "*                                                                                   *\n" +
                    "*                                                                                   *\n" +
                    "*                   Netty Http Server started on port {}.                         *\n" +
                    "*                                                                                   *\n" +
                    "*                                                                                   *\n" +
                    "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n";
            LOGGER.info(logBanner, port);
        });
        cf.channel().closeFuture().addListener(future -> {
            LOGGER.info("Netty Http Server Start Shutdown ............");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        });
    }

}
