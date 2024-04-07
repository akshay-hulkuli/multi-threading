package com.akshay.studentlibrarysec7;

import com.akshay.studentlibrarysec7.constants.Constants;
import com.akshay.studentlibrarysec7.domain.Book;
import com.akshay.studentlibrarysec7.domain.Student;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Student[] students = new Student[Constants.NUM_OF_STUDENTS];
        Book[] books = new Book[Constants.NUM_OF_BOOKS];
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.NUM_OF_STUDENTS);

        try {

            for (int i = 0; i < Constants.NUM_OF_BOOKS; i++) {
                books[i] = new Book(i);
            }

            for (int i = 0; i < Constants.NUM_OF_STUDENTS; i++) {
                executorService.execute(new Student(i, books));
            }

            Thread.sleep(10000);

            executorService.shutdownNow();
        } finally {
            executorService.shutdown();
        }

    }
}
