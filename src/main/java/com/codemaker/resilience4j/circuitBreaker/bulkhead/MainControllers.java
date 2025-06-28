package com.codemaker.resilience4j.circuitBreaker.bulkhead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainControllers {

    @Autowired
    private SemaphoreService semaphoreService;

    @GetMapping("/payment")
    public Object PaymentProcess(){
        return this.semaphoreService.PaymentProcess();
    }

}
