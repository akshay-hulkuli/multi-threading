package com.akshay.forkjoinsec9;

import java.util.concurrent.ForkJoinPool;

public class SimpleRecursiveActionExample {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(800);
        simpleRecursiveAction.invoke();
    }
}
