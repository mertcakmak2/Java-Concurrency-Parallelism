package com.main;

import com.CommonUtils;
import com.LoggerUtil;
import com.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.CommonUtils.stopWatch;

public class ComposeCompletableFutureService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        stopWatch.start();

        HelloWorldService service = new HelloWorldService();

        CompletableFuture<String> worldFuture = CompletableFuture
                .supplyAsync(() -> service.hello())
                .thenCompose((previous) -> service.worldFuture(previous));

        LoggerUtil.log("Res: " + worldFuture.get());

        stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ stopWatch.getTime());

    }
}
