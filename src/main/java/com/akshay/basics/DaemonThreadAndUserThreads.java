package com.akshay.basics;

public class DaemonThreadAndUserThreads {
    public static void main(String[] args) {
        System.out.println("The main thread is running -> thread name: " + Thread.currentThread().getName()
                + ", thread id: " + Thread.currentThread().getId());
        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new NormalWorker());
        t1.setDaemon(true);
        System.out.println("Is thread t1 a daemon thread -> " + t1.isDaemon());
        t1.start();
        t2.start();
    }
}

class DaemonWorker implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon thread is running");
        }
    }
}

class NormalWorker implements Runnable {
    @Override
    public void run() {
        System.out.println("Normal thread is running");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Normal thread has completed execution");
    }
}
