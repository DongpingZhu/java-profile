package com.test.mybatis.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Order(1)
public class TestCommandLineRunner implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("开始运行testCommandLineRunner-----------------------------------");
    }
}
