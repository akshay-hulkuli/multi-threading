package com.akshay.parallelizationsec8;

import java.util.Random;

public class SumExample {
    public static void main(String[] args) {
        int[] nums = createArray(1000000000);
        ArraySum arraySum = new ArraySum(nums);
        long startTime = System.currentTimeMillis();
        long sum = arraySum.sum();
        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time is : " + (endTime - startTime));
        System.out.println("Sum is : " + sum);


        int numOfThreads = Runtime.getRuntime().availableProcessors();
        ArraySumOptimized arraySumOptimized = new ArraySumOptimized(numOfThreads);
        startTime = System.currentTimeMillis();
        sum = arraySumOptimized.sum(nums);
        endTime = System.currentTimeMillis();
        System.out.println("Elapsed time is : " + (endTime - startTime));
        System.out.println("Sum is : " + sum);
    }

    private static int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(1000);
        }
        return array;
    }
}
