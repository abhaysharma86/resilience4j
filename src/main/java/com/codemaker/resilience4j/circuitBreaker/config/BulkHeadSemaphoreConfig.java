//package com.codemaker.resilience4j.circuitBreaker.config;
//
//
//import io.github.resilience4j.bulkhead.Bulkhead;
//import io.github.resilience4j.bulkhead.BulkheadConfig;
//import io.github.resilience4j.bulkhead.BulkheadRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class BulkHeadSemaphoreConfig {
//
//    @Bean
//    public BulkheadConfig customBulkheadConfig() {
//        return BulkheadConfig.custom()
//                .maxConcurrentCalls(2) // Allow only 2 concurrent requests
//                .maxWaitDuration(Duration.ZERO) // Reject immediately if full
//                .build();
//    }
//    @Bean
//    public BulkheadRegistry bulkheadRegistry(BulkheadConfig customBulkheadConfig) {
//        return BulkheadRegistry.of(customBulkheadConfig);
//    }
//
//    @Bean
//    public Bulkhead customBulkhead(BulkheadRegistry registry) {
//        return registry.bulkhead("customBulkhead");
//    }
//
//
//}
