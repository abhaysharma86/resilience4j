package com.codemaker.resilience4j.circuitBreaker.circuitBreaker;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@RestController
public class CircuitBreakerExample1 {
    @Autowired
    private ExternalService externalService;

    @Autowired
    private RestTemplate restCall;

    // Based on application properties file configuration

    @GetMapping("/")
    @CircuitBreaker(name = "externalApiCircuitBreaker", fallbackMethod = "follBackMethod")
    public Object recordsHandler() {
        System.out.println("Request received" + LocalDateTime.now());
        ResponseEntity<Object> forEntity = restCall.getForEntity("http://localhost:9090/", Object.class);
        return forEntity.getBody();
    }


    // Based on Custom configuration file

    @GetMapping("/records")
    public Object withCustomCircuitBreakerRecordsHandler() {
        return this.externalService.callExternalService();
    }


}
