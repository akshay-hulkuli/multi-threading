package com.akshay.streamssec10;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class ParallelizationInStreamsSEC5 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(sum(1000000000));
        long end = System.currentTimeMillis();
        System.out.println("Time taken by sequential way " + (end - start));

        start = System.currentTimeMillis();
        System.out.println(parallelSum(1000000000));
        end = System.currentTimeMillis();
        System.out.println("Time taken by parallel sum " + (end - start));

        start = System.currentTimeMillis();
        System.out.println(IntStream.rangeClosed(2, Integer.MAX_VALUE/100).filter(ParallelizationInStreamsSEC5::isPrime).count());
        end = System.currentTimeMillis();
        System.out.println("Time taken by sequential prime stream " + (end - start));

        start = System.currentTimeMillis();
        System.out.println(IntStream.rangeClosed(2, Integer.MAX_VALUE/100).parallel().filter(ParallelizationInStreamsSEC5::isPrime).count());
        end = System.currentTimeMillis();
        System.out.println("Time taken by parallel prime stream " + (end - start));

    }

    private static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static long sum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    // internally uses fork-join approach
    private static long parallelSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }
}
