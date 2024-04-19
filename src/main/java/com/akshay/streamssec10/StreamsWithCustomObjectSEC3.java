package com.akshay.streamssec10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        List<String> longestBooks = books.stream().filter(book -> book.getPages() > 100).map(Book::getTitle).limit(2).toList();
        longestBooks.forEach(System.out::println);

        System.out.println("-------------");

        books.stream().filter(book -> book.getTitle().split(" ").length == 2).forEach(System.out::println);

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
