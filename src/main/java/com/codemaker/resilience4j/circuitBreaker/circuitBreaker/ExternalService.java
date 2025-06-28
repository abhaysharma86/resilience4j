package com.codemaker.resilience4j.circuitBreaker.circuitBreaker;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Service
public class ExternalService {
    private final CircuitBreaker circuitBreaker;
    private final RestTemplate restCall;

    public ExternalService(Resilience4JCircuitBreakerFactory factory, RestTemplate restCall) {
        this.circuitBreaker = factory.create("customCB");
        this.restCall = restCall;
    }

    public Object callExternalService(){
        Supplier<Object> supplier = () ->{
            System.out.println("Request received" + LocalDateTime.now());
            ResponseEntity<Object> forEntity = restCall.getForEntity("http://localhost:9090//", Object.class);
            return forEntity.getBody();
        };
        return circuitBreaker.run(supplier, throwable -> follBackMethod(throwable));
    }

    public String follBackMethod(Throwable e) {
        return "Service is not reachable" + e.getMessage();
    }

}
