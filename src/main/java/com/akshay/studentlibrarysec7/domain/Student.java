package com.akshay.studentlibrarysec7.domain;

import com.akshay.studentlibrarysec7.constants.Constants;

import java.util.Random;

public class Student implements Runnable {
    private int id;
    private Book[] books;
    private Random rand = new Random();

    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
    }

    @Override
    public void run() {
        while (true) {
            int bookIndex = rand.nextInt(Constants.NUM_OF_BOOKS);
            Book book = books[bookIndex];
            try {
                book.read(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Student -> id: " + id;
    }
}
