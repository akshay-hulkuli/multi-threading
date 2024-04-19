package com.akshay.streamssec10;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsWithCustomObjectSEC3 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("ABC ASD", "kkak", 10, Type.PHILOSOPHY));
        books.add(new Book("ASDF", "qwe", 100, Type.HISTORICAL));
        books.add(new Book("ERT", "reytry", 590, Type.THRILLER));
        books.add(new Book("DGFG", "gfghfg", 160, Type.FICTION));
        books.add(new Book("dFGC", "asfghfg", 30, Type.PHILOSOPHY));
        books.add(new Book("NBMBBM", "asfghfg", 930, Type.NOVEL));

//        books.stream().forEach(System.out::println);
        List<Book> philosophicalBook =  books.stream()
                .filter(book -> book.getType() == Type.PHILOSOPHY)
                .sorted(Comparator.comparing(Book::getAuthor))
//                .map(Book::getTitle)
                .collect(Collectors.toList());
        philosophicalBook.forEach(System.out::println);

        System.out.println("-------------");

        Map<Type, List<Book>> booksByType = books.stream().collect(Collectors.groupingBy(Book::getType));

        booksByType.entrySet().stream().forEach(System.out::println);

        System.out.println("-------------");

        // Loop fusion -> here filter and map are going to merged and will be performed together on one element
        // Short circulating -> since limit 2 is present the streams terminates even before processing all items
        List<String> longestBooks = books.stream()
                .filter(book -> {
                    System.out.println("Filtering book : " + book.getTitle());
                    return book.getPages() > 100;
                })
                .map(book -> {
                    System.out.println("Mapping Book : " + book.getTitle());
                    return book.getTitle();
                })
                .limit(2).toList();

        longestBooks.forEach(System.out::println);

        System.out.println("-------------");

        books.stream().filter(book -> book.getTitle().split(" ").length == 2).forEach(System.out::println);
        System.out.println("-------------");

        // External and Internal Iteration

        // External Iterator -->
        List<String> titles = new ArrayList<>();

        // Both of ways are sequential

        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            titles.add(book.getTitle());
        }
        for(Book book : books) {
            titles.add(book.getTitle());
        }

        // Internal iteration
        List<String> titles2 = books.stream().map(Book::getTitle).collect(Collectors.toList());


        // map() and flatmap() methods
        books.stream().map(book -> book.getTitle().length()).forEach(System.out::println);
        System.out.println("-------------");

        books.stream().map(book -> book.getTitle().split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

        System.out.println("-------------");

        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(4, 5);
        List<List<Integer>> pairs = nums1.stream().flatMap(i -> nums2.stream().map(j -> Arrays.asList(i,j))).collect(Collectors.toList());

        // Optional Object -> used to handle null pointer exceptions. This is container to hold a value that could be null;
        List<Integer> nums= Arrays.asList(1,2,3,4);
        Optional<Integer> result = nums.stream().reduce(Integer::max);
        result.ifPresent(System.out::println);

        books.stream().reduce((b1, b2) -> b1.getPages() > b2.getPages() ? b1 : b2).ifPresent(book -> System.out.println(book));

        IntStream intStream = books.stream().mapToInt(book -> book.getPages());
        Stream<Integer> stream = intStream.boxed();
        System.out.println(stream.max(Comparator.naturalOrder()));

        OptionalInt optionalInt = books.stream().mapToInt(Book::getPages).max();
        optionalInt.ifPresent(System.out::println);

        System.out.println("---------------");

        boolean allMatch = books.stream().allMatch(book -> book.getPages() < 2000);
        boolean nonMatch = books.stream().noneMatch(book -> book.getPages() < 2000);
        Optional<Book> book = books.stream().filter(b -> b.getTitle().startsWith("A")).findFirst();
        book.ifPresent(System.out::println);
        Optional<Book> book2 = books.stream().filter(b -> b.getTitle().startsWith("A")).findAny();
        book2.ifPresent(System.out::println);
    }
}

class Book {
    private String title;
    private String author;
    private int pages;
    private Type type;
    public Book(String title, String author, int pages, Type type) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.type = type;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", type=" + type +
                '}';
    }
}

enum Type {
    NOVEL, FICTION, HISTORICAL, THRILLER, PHILOSOPHY
}
