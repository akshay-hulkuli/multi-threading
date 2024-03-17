package com.akshay.mutithreadingconceptssec3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
    INSTANCE;
    private Semaphore semaphore = new Semaphore(3, true);

    public void download() {
        try {
            semaphore.acquire();
            downloadData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void downloadData() {
        try {
            System.out.println("Download is started");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Download is completed");
    }
}

public class SemaphoreExampleSEC5 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 12; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.download();
                }
            });
        }
    }
}

//class Downloader {
//    private Semaphore semaphore = new Semaphore(3, true);
//
//    public void download() {
//        try {
//            semaphore.acquire();
//            downloadData();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            semaphore.release();
//        }
//    }
//
//    private void downloadData() {
//        try {
//            System.out.println("Download is started");
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Download is completed");
//    }
//}
//
//public class SemaphoreExampleSEC5 {
//
//    public static void main(String[] args) {
//        Downloader downloader = new Downloader();
//        for (int i = 0; i < 12; i++) {
//            new Thread(downloader::download).start();
//        }
//    }
//}
