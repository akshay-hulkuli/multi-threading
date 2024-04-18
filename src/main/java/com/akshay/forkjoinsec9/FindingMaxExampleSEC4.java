package com.akshay.forkjoinsec9;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindingMaxExampleSEC4 {
    public static void main(String[] args) {
        int[] nums = createArray(1000000000);
        SequentialMax sequentialMax = new SequentialMax();
        long start = System.currentTimeMillis();
        System.out.println(sequentialMax.findMax(nums));
        long end = System.currentTimeMillis();
        System.out.println("Sequential time taken : " + (end - start));

        ParallelMax parallelMax = new ParallelMax(nums, 0, nums.length - 1);
        ForkJoinPool forkJoinPool = new ForkJoinPool(12);
        start = System.currentTimeMillis();
        System.out.println(forkJoinPool.invoke(parallelMax));
        end = System.currentTimeMillis();
        System.out.println("Parallel time taken : " + (end - start));
    }

    private static int[] createArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }
}

class SequentialMax {
    public int findMax(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }
}

class ParallelMax extends RecursiveTask<Integer> {
    private int[] nums;
    private int low;
    private int high;

    public ParallelMax(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {
        if (high - low <= 100000) {
            int max = nums[low];
            for (int i = low + 1; i <= high; i++) {
                if (max < nums[i]) {
                    max = nums[i];
                }
            }
            return max;
        } else {
            int mid = low + (high - low) / 2;
            ParallelMax parallelMax = new ParallelMax(nums, low, mid);
            ParallelMax parallelMax2 = new ParallelMax(nums, mid + 1, high);
            return Math.max(parallelMax.compute(), parallelMax2.compute());
        }
    }
}


