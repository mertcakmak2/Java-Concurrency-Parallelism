package com.main;

import com.CommonUtils;
import com.LoggerUtil;
import com.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureService {

    public static void main(String[] args) {

        HelloWorldService service = new HelloWorldService();

        CompletableFuture.supplyAsync(() -> service.helloWorld())
                .thenApply(String::toUpperCase) // Transform data
                .thenAccept(result -> LoggerUtil.log(result))
                .join();

        LoggerUtil.log("Done!");
        //CommonUtils.delay(1000); without .join method

    }
}
