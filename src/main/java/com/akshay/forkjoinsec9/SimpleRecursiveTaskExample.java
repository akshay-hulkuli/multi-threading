package com.akshay.forkjoinsec9;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTaskExample {
    public static void main(String[] args) {
        SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(10000);
        System.out.println(simpleRecursiveTask.invoke());
    }
}

class SimpleRecursiveTask extends RecursiveTask<Integer> {
    int number;

    public SimpleRecursiveTask(int number) {
        this.number = number;
    }

    @Override
    protected Integer compute() {
        if (number < 10) {
            System.out.println("SimpleRecursiveTask is small to double, number " + number);
            return number * 2;
        } else {
            System.out.println("SimpleRecursiveTask is large, hence splitting, number " + number);
            SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(number / 2);
            SimpleRecursiveTask simpleRecursiveTask2 = new SimpleRecursiveTask(number / 2);
            simpleRecursiveTask.fork();
            simpleRecursiveTask2.fork();
            int intermediateResult = 0;
            intermediateResult += simpleRecursiveTask.join();
            intermediateResult += simpleRecursiveTask2.join();
            return intermediateResult;
        }
    }
}
