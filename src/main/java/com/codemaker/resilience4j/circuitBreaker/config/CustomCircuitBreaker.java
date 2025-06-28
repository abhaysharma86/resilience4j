package com.codemaker.resilience4j.circuitBreaker.config;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomCircuitBreaker {
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> getCustomCR() {
        return factory -> factory.configure(builder -> builder
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .failureRateThreshold(50)
                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                        .slidingWindowSize(10)
                        .automaticTransitionFromOpenToHalfOpenEnabled(true)
                        .build()), "customCB");
    }


    @Bean
    public RestTemplate restCall(){
        return new RestTemplate();
    }

}
