package com.akshay.streamssec10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class StreamsWithStringsSEC2 {
    public static void main(String[] args) {
        String[] names = {"Akshay", "Aakash", "Anoop", "Hari", "Liki", "Suresh", "Prakash"};
        Arrays.stream(names).forEach(System.out::println);
        Stream.of(names).forEach(System.out::println);
        System.out.println("---------------");
        Stream.of(names).sorted().forEach(System.out::println);
        System.out.println("---------------");
        Stream.of(names).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("---------------");
        Stream.of(names).filter(str -> str.startsWith("A")).forEach(System.out::println);
        System.out.println("---------------");
        Stream<String> namesStream = Stream.of(names);
        namesStream.forEach(System.out::println);
        // This throws error a stream can be iterated only once.
//        namesStream.forEach(System.out::println);
    }
}
