package com.technicaltestpinapp.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetricsManager {

    @Autowired
    MeterRegistry registry;

    public Counter counter(String name, String... tags){
        return registry.counter(name, tags);
    }

}
