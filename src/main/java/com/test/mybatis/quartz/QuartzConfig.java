package com.test.mybatis.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(QuartzJob1.class).storeDurably().build();
    }
    @Bean
    public Trigger trigger(){

        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();// 每1s一次,永远

        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(simpleScheduleBuilder).build();
    }

}
