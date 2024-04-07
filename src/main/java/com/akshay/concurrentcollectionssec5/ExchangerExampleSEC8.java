package com.akshay.concurrentcollectionssec5;

import java.util.concurrent.Exchanger;

public class ExchangerExampleSEC8 {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        FirstThread thread1 = new FirstThread(exchanger);
        SecondThread thread2 = new SecondThread(exchanger);

        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}

class FirstThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
        counter = 0;
    }


    @Override
    public void run() {
        while (true) {
            try {
                counter++;
                System.out.println("The first thread incremented the counter" + counter);
                counter = exchanger.exchange(counter);
                System.out.println("The first thread got the counter" + counter);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class SecondThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
        counter = 0;
    }


    @Override
    public void run() {
        while (true) {
            try {
                counter--;
                System.out.println("The second thread decremented the counter" + counter);
                counter = exchanger.exchange(counter);
                System.out.println("The second thread got the counter" + counter);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
