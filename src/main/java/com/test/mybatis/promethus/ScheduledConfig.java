package com.test.mybatis.promethus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledConfig {

    @Autowired
    MeterConfig meterConfig;

    int count1 = 0;

    @Async
    @Scheduled(fixedDelay = 1000) // 每一秒监控一次
    public void increment(){
        count1++;
        meterConfig.counter.increment();
        meterConfig.map.put("x", (double) count1);
//        System.out.println(count1);
    }
}
