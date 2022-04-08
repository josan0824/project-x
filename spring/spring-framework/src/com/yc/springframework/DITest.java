package com.yc.springframework;

public class DITest {
    Book book;

    public DITest() {

    }

    public void setBook(Book book) {
        this.book = book;
    }

    public DITest(Book book) {
        this.book = book;
    }
}
