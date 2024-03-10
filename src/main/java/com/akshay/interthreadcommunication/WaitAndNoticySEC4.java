package com.akshay.interthreadcommunication;

/**
 * Inorder to wait and notify work we need to ensure that they acquire same object's intrinsic locks
 */
class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce method....");
            wait();
            System.out.println("Again in the producer method....");
        }
    }
    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Consume method is executed");
            notify();
            // notify will not hand over lock immediately.
            Thread.sleep(5000);
        }
    }
}
public class WaitAndNoticySEC4 {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
