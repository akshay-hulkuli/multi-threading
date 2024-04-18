package com.akshay.parallelizationsec8;

import java.util.Random;

public class App {
    public static void main(String[] args) {
//        int[] nums = {5, -1, 0, 7, 2, 3, 2, 1, 0, 1, 2};
//        MergeSort mergeSort = new MergeSort(nums);
//        mergeSort.sort();
//        mergeSort.showArray();
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads: " + numOfThreads);
        int[] numbers1 = new int[10000000];
        numbers1 = createArray(numbers1.length);
        int[] numbers2 = new int[numbers1.length];
        for (int i = 0; i < numbers1.length; i++) {
            numbers2[i] = numbers1[i];
        }

        MergeSortOptimized mergeSortOptimized = new MergeSortOptimized(numbers2);
        long startTime = System.currentTimeMillis();
        mergeSortOptimized.parallelMergeSort(0, numbers1.length - 1, numOfThreads);
        long endTime = System.currentTimeMillis();
        System.out.println("parallel Merge sort took " + (endTime - startTime) + " ms");

        MergeSort mergeSort = new MergeSort(numbers2);
        long sortStartTime = System.currentTimeMillis();
        mergeSort.sort();
        long endSortTime = System.currentTimeMillis();
        System.out.println("sequential sort took " + (endSortTime - sortStartTime) + " ms");

    }

    private static int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000);
        }
        return array;
    }
}
