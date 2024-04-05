package com.akshay.executorservicessec4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarkerUpdater implements Runnable  {
    @Override
    public void run() {
        System.out.println("Getting stock market updates..... by the thread " + Thread.currentThread().getName());
    }
}
public class ScheduledExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleWithFixedDelay(new StockMarkerUpdater(), 1, 3, TimeUnit.SECONDS);
    }
}
