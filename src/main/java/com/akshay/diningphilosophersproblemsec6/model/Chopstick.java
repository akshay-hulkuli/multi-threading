package com.akshay.diningphilosophersproblemsec6.model;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
        // this is where we simulate deadlock
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println("Chopstick : " + id + "  picked up by " + philosopher + " at " + state);
            return true;
        } else {
            System.out.println("Chopstick : " + id + "  could not be picked up by " + philosopher + " at " + state);
            return false;
        }
    }

    public void putDown(Philosopher philosopher) throws InterruptedException {
        lock.unlock();
        System.out.println("Chopstick " + id + " Put down by " + philosopher);
    }

    @Override
    public String toString() {
        return "Chopstick id " + id;
    }
}
