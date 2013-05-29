package com.example.bookshelf.models;

public class Book {

    private static int idGen = 0;
    private int id;
    public String title;
    public Author author;

    public Book(String title, Author author) {
        this.id = idGen++;
        this.title = title;
        this.author = author;
    }

}
