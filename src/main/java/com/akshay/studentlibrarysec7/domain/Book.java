package com.akshay.studentlibrarysec7.domain;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int id;
    private Lock lock;

    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException {
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println(student + " is Reading the book " + this);
                TimeUnit.SECONDS.sleep(2);
                System.out.println(student + " finished Reading the book " + this);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(student + " could not get the book " + this);
        }
    }

    @Override
    public String toString() {
        return "Book [id=" + id + "]";
    }

}
