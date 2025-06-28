package com.codemaker.resilience4j.circuitBreaker.bulkhead;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class SemaphoreService {

    @Autowired
    private RestTemplate restCall;


    @Bulkhead(name = "paymentService", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "fallback")
    public Object PaymentProcess() {
        System.out.println("Processing payment by thread: " + Thread.currentThread().getName() + " time " + LocalDateTime.now());

        ResponseEntity<Object> forEntity = restCall.getForEntity("http://localhost:9090//", Object.class);
        return forEntity.getBody();
    }



//    This for custom config file

//    public Object paymentProcessWithConfig() {
//        Callable<Object> guardedCall = decorateCallable(bulkhead, () -> {
//            System.out.println("Processing payment by thread: "
//                    + Thread.currentThread().getName()
//                    + " at " + LocalDateTime.now());
//            ResponseEntity<Object> response = restCall.getForEntity(
//                    "http://localhost:9090/", Object.class);
//            return response.getBody();
//        });
//
//        ScheduledFuture<Object> future = scheduler.schedule(guardedCall, 1, TimeUnit.SECONDS);
//
//        try {
//            return future.get(); // wait and return result
//        } catch (BulkheadFullException e) {
//            return "Too many concurrent requests.";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }


    public String fallback(Throwable t) {
        return "Too many requests, please try again later.";
    }

}
