package com.codemaker.resilience4j.circuitBreaker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ServerResourceExample {

    @GetMapping("/users")
    public List<String> getRecordsList(){
        return Arrays.asList("Jon","Jimi","Roshan","Mohan","Ramesh");
    }

}
