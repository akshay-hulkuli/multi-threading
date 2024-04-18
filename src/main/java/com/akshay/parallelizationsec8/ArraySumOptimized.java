package com.akshay.parallelizationsec8;


public class ArraySumOptimized {

    private ParallelWorker[] parallelWorkers;
    private int numOfThreads;

    public ArraySumOptimized(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.parallelWorkers = new ParallelWorker[numOfThreads];
    }

    public long sum(int[] nums) {
        int size = (int) Math.ceil(nums.length * 1.0 / this.numOfThreads);
        for (int i = 0; i < numOfThreads; i++) {
            parallelWorkers[i] = new ParallelWorker(nums, i * size, (i + 1) * size);
            parallelWorkers[i].start();
        }

        try {
            for (ParallelWorker parallelWorker : parallelWorkers) {
                parallelWorker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long sum = 0;
        for(ParallelWorker parallelWorker : parallelWorkers) {
            sum += parallelWorker.getSum();
        }
        return sum;
    }

}

class ParallelWorker extends Thread {
    int nums[];
    int low;
    int high;
    long partialSum;

    public ParallelWorker(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = Math.min(high, nums.length);
    }

    @Override
    public void run() {
        partialSum = 0;
        for (int i = low; i < high; i++) {
            partialSum += nums[i];
        }
    }

    public long getSum() {
        return partialSum;
    }
}
