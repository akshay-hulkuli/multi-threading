package com.akshay.mutithreadingconceptssec3;

public class VolatileKeywordSEC1 {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);
        System.out.println("Algorithm is terminated");

    }
}

class Worker implements Runnable {

    // This won't be cached
    private volatile boolean terminated;

    @Override
    public void run() {
        while(!terminated) {
            System.out.println("Worker class is running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}
