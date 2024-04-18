package com.akshay.parallelizationsec8;

public class ArraySum {
    int nums[];

    public ArraySum(int[] nums) {
        this.nums = nums;
    }

    public long sum() {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
