package com.test.netty.nio.niosocket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server1 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888),60);
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println(selectionKey.interestOps());
        System.out.println(selectionKey.readyOps());
        System.out.println(selectionKey.channel());
        System.out.println(selectionKey.selector());
        System.out.println(selectionKey.attachment());



        serverSocketChannel.close();
    }
}
