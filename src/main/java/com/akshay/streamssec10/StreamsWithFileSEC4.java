package com.akshay.streamssec10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class StreamsWithFileSEC4 {
    public static void main(String[] args) throws IOException {
        String path = "/home/akshaya/java-core/multi-threading/src/main/java/com/akshay/streamssec10/Names";
        Stream<String> stream = Files.lines(Paths.get(path));
        List<String> names  = stream.toList();
        names.forEach(System.out::println);
    }
}
