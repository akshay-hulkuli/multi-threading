package com.akshay.concurrentcollectionssec5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


/**
 * BlockingQueue can be initialized with DelayQueue.
 * This is an unbounded queue.
 * DelayQueue stores objects that implement Delay interface.
 * We need to override 2 methods from Delay interface.
 * compareTo() which compares 2 Delay objects, must be done using the delay.
 * getDelay method, which is used to convert the number stored as delay into right time units.
 */

public class DelayQueueSEC5 {
    public static void main(String[] args) {

        BlockingQueue<DelayWorker> queue = new DelayQueue<>();
        try {
            queue.put(new DelayWorker(2000, "This is the first message"));
            queue.put(new DelayWorker(1000, "This is the second message"));
            queue.put(new DelayWorker(10000, "This is the third message"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take().toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class DelayWorker implements Delayed {

    private final long delay;
    private String message;

    public DelayWorker(long delay, String message) {
        this.delay = System.currentTimeMillis() + delay;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
//        if (delay < ((DelayWorker) o).getDelayedTime()) {
//            return -1;
//        } else if (delay > ((DelayWorker) o).getDelayedTime()) {
//            return 1;
//        } else {
//            return 0;
//        }
        return Long.compare(delay, ((DelayWorker) o).getDelayedTime());
    }

    public long getDelayedTime() {
        return delay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker [delay=" + delay + ", message=" + message + "]";
    }

}
