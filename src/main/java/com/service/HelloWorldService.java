package com.service;

import java.util.concurrent.CompletableFuture;

import static com.CommonUtils.delay;
import static com.LoggerUtil.log;

public class HelloWorldService {

    public  String helloWorld() {
        delay(1000);
        log("inside helloWorld");
        return "hello world";
        //throw new IllegalStateException("exception");
    }

    public  String hello() {
        delay(1000);
        log("inside hello");
        return "hello";
    }

    public  String world() {
        delay(1000);
        log("inside world");
        return " world!";
    }

    public CompletableFuture<String> worldFuture(String input) {
        return CompletableFuture.supplyAsync(()->{
            delay(1000);
            return input+" world!";
        });
    }

}
