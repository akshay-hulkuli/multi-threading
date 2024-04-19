package com.akshay.streamssec10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelSaveExampleSEC6 {
    private static final String DIRECTORY = System.getProperty("user.dir") + "/test/";

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get(DIRECTORY));
        ParallelSaveExampleSEC6 parallelSaveExample = new ParallelSaveExampleSEC6();

        List<Person> people = parallelSaveExample.generatePeople(10000);
        long start = System.currentTimeMillis();
        people.stream().forEach(parallelSaveExample::save);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));

        long start2 = System.currentTimeMillis();
        people.stream().parallel().forEach(parallelSaveExample::save);
        long end2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (end2 - start2));
    }

    private void save(Person person) {
        try(FileInputStream fileInputStream = new FileInputStream(new File(DIRECTORY + person.getPersonId() + ".txt"))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Person> generatePeople(int i) {
        return Stream.iterate(0, n -> n+1).limit(i).map(x -> new Person(x)).collect(Collectors.toList());
    }
}
