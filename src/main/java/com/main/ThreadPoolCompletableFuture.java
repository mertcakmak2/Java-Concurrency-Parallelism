package com.main;

import com.CommonUtils;
import com.LoggerUtil;
import com.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.CommonUtils.stopWatch;

public class ThreadPoolCompletableFuture {

    public static void main(String[] args) {

        stopWatch.start();

        HelloWorldService service = new HelloWorldService();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> service.helloWorld(), executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> service.hello(), executorService);
        CompletableFuture<String> hiCp = CompletableFuture.supplyAsync(() -> {
            CommonUtils.delay(500);
            return "HI Completable Future";
        }, executorService);

        String res = hello
                .thenCombine(world, (h, w) -> {
                    LoggerUtil.log("thenCombine h/w");
                    return  h + " " + w;
                })
                .thenCombine(hiCp, (prev, current) -> {
                    LoggerUtil.log("thenCombine prem / current");
                    return prev + " " + current;
                })
                .thenApply(s -> {
                    LoggerUtil.log("thenApply");
                    return s.toUpperCase();
                })
                .join();

        LoggerUtil.log("Res: " + res);

        stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ stopWatch.getTime());
        executorService.shutdown();

    }
}
