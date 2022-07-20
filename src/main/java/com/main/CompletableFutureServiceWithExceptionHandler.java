package com.main;

import com.CommonUtils;
import com.LoggerUtil;
import com.service.HelloWorldService;
import lombok.extern.java.Log;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureServiceWithExceptionHandler {

    public static void main(String[] args) {

        HelloWorldService service = new HelloWorldService();

        CompletableFuture.supplyAsync(() -> service.helloWorld())
                .thenApply(String::toUpperCase) // Transform data
                .thenAccept(result -> LoggerUtil.log(result))
                .join();

        // with handle ( The code in handle block always runs. )
        CompletableFuture.supplyAsync(() -> service.helloWorld())
                .handle((res, e) -> {
                    if (e != null) {
                        LoggerUtil.log("Exception: " + e.getMessage());
                        return "";
                    }
                    LoggerUtil.log("Handle result: " + res);
                    return res;
                })
                .thenApply(String::toUpperCase) // Transform data
                .thenAccept(result -> LoggerUtil.log(result))
                .join();


        // with exceptionally
        CompletableFuture.supplyAsync(() -> service.helloWorld())
                .exceptionally((e) -> {
                    LoggerUtil.log("Exception: " + e.getMessage());
                    //throw new IllegalStateException();
                    return "";
                })
                .thenApply(String::toUpperCase) // Transform data
                .thenAccept(result -> LoggerUtil.log(result))
                .join();

        LoggerUtil.log("Done!");
        //CommonUtils.delay(1000); without .join method

    }
}
