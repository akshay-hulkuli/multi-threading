package com.akshay.concurrentcollectionssec5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueSEC6 {
    public static void main(String[] args) {
        BlockingQueue<Address> queue = new PriorityBlockingQueue<>();
        WorkerOne workerOne = new WorkerOne(queue);
        WorkerSecond workerSecond = new WorkerSecond(queue);

        new Thread(workerOne).start();
        new Thread(workerSecond).start();

    }
}

class Address implements Comparable<Address> {
    private int houseNo;
    private String street;
    private String city;
    private String state;

    public Address(int houseNo, String street, String city, String state) {
        this.houseNo = houseNo;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    @Override
    public int compareTo(Address o) {
        return Integer.compare(this.houseNo, o.houseNo);
    }

    @Override
    public String toString() {
        return "Address [houseNo=" + houseNo + ", street=" + street + ", city=" + city + ", state=" + state + "]";
    }
}

class WorkerOne implements Runnable {

    private final BlockingQueue<Address> priorityBlockingQueue;

    public WorkerOne(BlockingQueue<Address> priorityBlockingQueue) {
        this.priorityBlockingQueue = priorityBlockingQueue;
    }

    @Override
    public void run() {
        try {
            priorityBlockingQueue.put(new Address(98, "asd", "asd", "asd"));
            priorityBlockingQueue.put(new Address(18, "asd", "asd", "asd"));
            priorityBlockingQueue.put(new Address(188, "asd", "asd", "asd"));
            Thread.sleep(2000);
            priorityBlockingQueue.put(new Address(8, "asd", "asd", "asd"));
            Thread.sleep(2000);
            priorityBlockingQueue.put(new Address(918, "asd", "asd", "asd"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WorkerSecond implements Runnable {

    private final BlockingQueue<Address> priorityBlockingQueue;

    public WorkerSecond(BlockingQueue<Address> priorityBlockingQueue) {
        this.priorityBlockingQueue = priorityBlockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(priorityBlockingQueue.take());
            Thread.sleep(1000);
            System.out.println(priorityBlockingQueue.take());
            Thread.sleep(1000);
            System.out.println(priorityBlockingQueue.take());
            Thread.sleep(1000);
            System.out.println(priorityBlockingQueue.take());
            System.out.println(priorityBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
