package com.example.demo.controller;

import com.example.demo.service.Service;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private Service service;
    private final MeterRegistry meterRegistry;

    public Controller(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/2xx")
    public String simulate2xxResponse() {
        meterRegistry.counter("orders.2xx","status","OK").increment();
        service.serviceMethod(1);
        return "Got 2xx Response";
    }

    @GetMapping("/5xx")
    public String simulate5xxResponse() {
        meterRegistry.counter("orders.5xx","status","NOTOK").increment();
        service.serviceMethod(2);
        return "Got 5xx Response";
    }

    @PostMapping("/alert-hook")
    public void receiveAlertHook(@RequestBody Map request) throws Exception {
        System.out.println(request);

    }
}