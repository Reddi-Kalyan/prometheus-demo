package com.example.demo.service;

import io.micrometer.core.instrument.MeterRegistry;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    private final MeterRegistry meterRegistry;

    public ServiceImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public String serviceMethod(int i){

        if(i == 2){
            throw new RuntimeException("exception");
        }

        meterRegistry.counter("calls.to.service","status","OK").increment();

        return "success";
    }
}
