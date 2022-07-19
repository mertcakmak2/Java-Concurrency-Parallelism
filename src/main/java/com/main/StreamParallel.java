package com.main;

import com.LoggerUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static com.CommonUtils.delay;
import static com.CommonUtils.stopWatch;

public class StreamParallel {

    public static void main(String[] args) {

        stopWatch.start();

        /*
        Set<Integer> setOfNumbers =  Set.of(1,2,3,4);
        // => Total Time Taken : 581 (parallelStream)
        // => Total Time Taken : 2070 (stream)
        Set<Integer> numbers = setOfNumbers.parallelStream().map(number ->{
            delay(500);
            return number*2;
        }).collect(Collectors.toSet());
        LoggerUtil.log("numbers "+ numbers);
        */


        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        // => Total Time Taken : 2099 (stream)
        // => Total Time Taken : 582 (parallelStream)
        listOfNumbers.parallelStream().forEach(number -> {
            delay(500);
            System.out.println(number + " " + Thread.currentThread().getName());
        });


        stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ stopWatch.getTime());
    }
}
