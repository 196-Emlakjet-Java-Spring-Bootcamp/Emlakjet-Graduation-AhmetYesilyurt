package com.ysl.emlakjet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AppConfiguration {
    @Value("${executor.thread.capacity}")
    private Integer capacity;

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(capacity);
    }

}
