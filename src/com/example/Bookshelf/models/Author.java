package com.example.Bookshelf.models;


public class Author {

    private static int idGen = 0;
    private int id;
    public String name;
    public int booksCount = 0;

    public Author(String name) {
        this.id = idGen++;
        this.name = name;
    }
}
