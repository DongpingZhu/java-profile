package com.test.mybatis.async;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTest {

    @Scheduled(fixedRate = 1000 * 60)
    public void testCorn(){
        System.out.println("每过5秒打印一次--------------------------");
    }

}
