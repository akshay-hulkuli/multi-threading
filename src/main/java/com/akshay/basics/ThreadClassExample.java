package com.akshay.basics;

public class ThreadClassExample {
    public static void main(String[] args) {
        Thread t1 = new Runner4();
        Thread t2 = new Runner5();
        t1.start();
        t2.start();
    }

}

class Runner4 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("Runner1 : " + i);
        }
    }
}

class Runner5 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("Runner2 : " + i);
        }
    }
}
