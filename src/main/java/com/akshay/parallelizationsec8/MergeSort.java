package com.akshay.parallelizationsec8;

public class MergeSort {
    private int[] nums;
    private int[] tempArray;

    public MergeSort(int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    public void sort() {
        mergeSort(0, nums.length - 1);
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

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
