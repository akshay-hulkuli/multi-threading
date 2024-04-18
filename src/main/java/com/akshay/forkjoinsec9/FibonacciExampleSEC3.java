package com.akshay.forkjoinsec9;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciExampleSEC3 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new FibonacciExample(40)));
    }
}

class FibonacciExample extends RecursiveTask<Integer> {
    private int n;
    public FibonacciExample(int n) {
        this.n = n;
    }
    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FibonacciExample fib1 = new FibonacciExample(n - 1);
        FibonacciExample fib2 = new FibonacciExample(n - 2);
        fib2.fork();
        // we spanning only thread and reusing the current thread by calling compute
        return fib1.compute() + fib2.join();
    }
}
