package com.akshay.virtualthreadsec11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComparisonVirtualAndPlatformThreadsSEC2 {
    public static void main(String[] args) {

        /*
        Throws error fails to create 100000 platform/carrier threads
         */
//        for(int i=0; i<10000; i++) {
//            Thread.ofPlatform().name("platform-thread", 0).start(() -> {
//                System.out.println("Staring thread : " + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }


        /*
        Virtual threads using pool - no exception
         */
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 100000; i++) {
            service.submit(() -> {
                System.out.println("Staring thread : " + Thread.currentThread());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
