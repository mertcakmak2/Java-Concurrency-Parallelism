package com.main;

import com.CommonUtils;
import com.LoggerUtil;
import com.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.CommonUtils.stopWatch;

public class CombineCompletableFuture {

    public static void main(String[] args) {

        stopWatch.start();

        HelloWorldService service = new HelloWorldService();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> service.helloWorld());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> service.hello());
        CompletableFuture<String> hiCp = CompletableFuture.supplyAsync(() -> {
            CommonUtils.delay(500);
            return "HI Completable Future";
        });

        String res = hello
                .thenCombine(world, (h, w) -> h + " " + w)
                .thenCombine(hiCp, (prev, current) -> prev + " " + current)
                .thenApply(String::toUpperCase)
                .join();

        LoggerUtil.log("Res: " + res);

        stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ stopWatch.getTime());

    }
}
