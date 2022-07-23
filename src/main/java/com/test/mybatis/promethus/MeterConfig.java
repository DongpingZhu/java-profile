package com.test.mybatis.promethus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MeterConfig implements MeterBinder {

    public Counter counter;

    public Map<String, Double> map;
    public MeterConfig(){
        map = new HashMap<>();
    }
    public void bindTo(MeterRegistry registry){
        this.counter = Counter.builder("demo.counter").tags("name","counter").description("this is counter").register(registry);

        Gauge.builder("demo.gauge",map, x->x.get("x")).tag("name","guage").description("this is gauge").register(registry);

    }
}
