package com.akshay.parallelizationsec8;

public class MergeSortOptimized {
    private int[] nums;
    private int[] tempArray;

    public MergeSortOptimized(int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    private void mergeSort(int low, int high) {
        if(low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        merge(low, mid, high);
    }

    private void merge(int low, int mid, int high) {
        for(int i = low; i <= high; i++) {
            tempArray[i] = nums[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while(i <= mid && j <= high) {
            if(tempArray[i] <= tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }
            k++;
        }
        while(i <= mid) {
            nums[k] = tempArray[i];
            k++;
            i++;
        }
        while(j <= high) {
            nums[k] = tempArray[j];
            k++;
            j++;
        }
    }

    public void showArray() {
        for(int i : nums) {
            System.out.print(i + " ");
        }
    }

    public void parallelMergeSort(int low, int high, int numberOfThreads) {
        if(numberOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }
        int middle = (low + high) / 2;
        Thread leftSorter = createThread(low, middle, numberOfThreads);
        Thread rightSorter = createThread(middle + 1, high, numberOfThreads);
        leftSorter.start();
        rightSorter.start();
        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        merge(low, middle, high);
    }

    private Thread createThread(int low, int high, int numberOfThreads) {
        return new Thread(() -> {
            parallelMergeSort(low, high, numberOfThreads / 2);
        });
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
