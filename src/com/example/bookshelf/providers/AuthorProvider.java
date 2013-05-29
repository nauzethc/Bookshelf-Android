package com.example.bookshelf.providers;


import com.example.bookshelf.models.Author;

import java.util.ArrayList;

public class AuthorProvider {

    private static final ArrayList<Author> authors = new ArrayList<Author>() {{
        add( new Author("George R.R. Martin") );
        add( new Author("J.R.R. Tolkien") );
    }};

    public static ArrayList<Author> getAuthors() {
        return authors;
    }

    public static Author getAuthor(int position) {
        return authors.get(position);
    }

}
