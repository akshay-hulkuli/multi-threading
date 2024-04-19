package com.akshay.streamssec10;

import java.util.Arrays;

public class StreamsWithNumbersSEC1 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 4, 5, 6, 6, 2, 7, 6, 8, 3, 3};
        Arrays.stream(nums).forEach(System.out::println);
        System.out.println(Arrays.stream(nums).sum());
        System.out.println(Arrays.stream(nums).reduce(Integer::sum).getAsInt());
        Arrays.stream(nums).filter(n -> n > 4).forEach(System.out::println);
    }
}
