package com.test.mybatis.controller;

import com.test.mybatis.service.IDemo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController

public class WebFluxDemo {
    @Autowired
    private IDemo1 demo1;

    @GetMapping("/flux")
    public Mono<String> get(){
        Mono<String> result = Mono.fromSupplier(()->demo1.test1());
        return result;
    }
}
