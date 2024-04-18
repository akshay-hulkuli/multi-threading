package com.akshay.forkjoinsec9;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortUsingForkJoinSEC5 {
    public static void main(String[] args) {

    }
}

//class MergeSortUsingForkJoin extends RecursiveAction {
//    int[] arr;
//    int[] tempArray;
//    public MergeSortUsingForkJoin(int[] arr) {
//        this.arr = arr;
//        this.tempArray = new int[arr.length];
//    }
//    @Override
//    protected void compute() {
//        if (arr.length < 50) {
//            mergeSort();
//        }
//        int mid = arr.length / 2;
//        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
//        int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);
//        MergeSortUsingForkJoin left = new MergeSortUsingForkJoin(leftArr);
//        MergeSortUsingForkJoin right = new MergeSortUsingForkJoin(rightArr);
//        left.compute();
//        right.invoke();
//        merge();
//    }
//
//    private void mergeSort(int low, int high) {
//        if(low >= high) {
//            return;
//        }
//        int mid = (low + high) / 2;
//        mergeSort(low, mid);
//        mergeSort(mid + 1, high);
//        merge(low, mid, high);
//    }
//
//    private void merge(int low, int mid, int high) {
//        for(int i = low; i <= high; i++) {
//            tempArray[i] = arr[i];
//        }
//        int i = low;
//        int j = mid + 1;
//        int k = low;
//        while(i <= mid && j <= high) {
//            if(tempArray[i] <= tempArray[j]) {
//                arr[k] = tempArray[i];
//                i++;
//            } else {
//                arr[k] = tempArray[j];
//                j++;
//            }
//            k++;
//        }
//        while(i <= mid) {
//            nums[k] = tempArray[i];
//            k++;
//            i++;
//        }
//        while(j <= high) {
//            nums[k] = tempArray[j];
//            k++;
//            j++;
//        }
//    }
//}