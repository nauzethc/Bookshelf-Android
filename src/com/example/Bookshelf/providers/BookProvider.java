package com.example.Bookshelf.providers;


import com.example.Bookshelf.models.Author;
import com.example.Bookshelf.models.Book;

import java.util.ArrayList;

public class BookProvider {

    public static ArrayList<Book> getBooks() {

        ArrayList<Author> authors = AuthorProvider.getAuthors();
        Author martin = authors.get(0);
        Author tolkien = authors.get(1);

        // Load books
        ArrayList<Book> books = new ArrayList<Book>();
        books.add( new Book("Game of Thrones", martin) );
        books.add( new Book("Clash of Kings", martin) );
        books.add( new Book("Storm of Swords", martin) );
        books.add( new Book("Feast for Crows", martin) );
        books.add( new Book("Dance with Dragons", martin) );
        books.add( new Book("The Lord of the Rings", tolkien) );
        books.add( new Book("The Hobbit", tolkien) );
        books.add( new Book("Silmarillion", tolkien) );

        // Return data
        return books;
    }
}
