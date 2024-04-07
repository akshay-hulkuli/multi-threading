package com.akshay.diningphilosophersproblemsec6.model;

import java.util.Random;

public class Philosopher implements Runnable {
    private int id;
    private volatile boolean isFull;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
        this.eatingCounter = 0;
    }

    @Override
    public void run() {

        try {
            // After eating a lot terminate the thread
            while (!isFull) {
                think();
                if (leftChopstick.pickUp(this, State.LEFT)) {
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this);
                    }
                    leftChopstick.putDown(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + this + " is thinking");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + this + " is eating");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public boolean isFull() {
        return isFull;
    }

    @Override
    public String toString() {
        return "Philosopher -> id :" + id + " is full :" + isFull + " leftChopstick:" + leftChopstick + " rightChopstick:" + rightChopstick
                + " eatingCounter:" + eatingCounter;
    }
}
