/**
 * Copyright 2013-2033 Xia Jun(3979434@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.farsunset.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.farsunset.httpserver;

import com.farsunset.httpserver.netty.annotation.NettyHttpHandler;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// 使用Netty构建HttpServer来实现轻量级的RestfulAPIs
@SpringBootApplication()
@ComponentScan(includeFilters = @ComponentScan.Filter(NettyHttpHandler.class))

public class HttpServerApplication {

    public static void main(String[] args) {
        // NONE 表示这个应用程序不是web类型的应用程序
        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(HttpServerApplication.class).web(WebApplicationType.NONE);
        ConfigurableApplicationContext run = applicationBuilder.run(args);

    }
}