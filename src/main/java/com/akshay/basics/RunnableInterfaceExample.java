package com.akshay.basics;


class Runner1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1: " + i);
        }
    }
}

public class RunnableInterfaceExample
{
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Runner2: " + i);
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runner3: " + i);
            }
        });
        t1.start();
        t2.start();
        t3.start();

    }
}