package com.example.bookshelf.providers;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.bookshelf.models.Author;
import com.example.bookshelf.models.Book;

import java.util.ArrayList;

public class BookProvider extends ContentProvider {
    
	// URI
	private static final String AUTHORITY = "com.example.bookshelf";
	private static final String URI = "content://" + AUTHORITY + "/books";
	private static final Uri CONTENT_URI = Uri.parse(URI);
	
	// Table definition
	public static final class Books implements BaseColumns {
		private Books(){}
		// Columns
		public static final String TITLE = "title";
		public static final String YEAR = "year";
		public static final String AUTHOR = "author";
	}
	
	// URI match cases
	private static final int BOOKS = 1;
	private static final int BOOKS_ID = 2;
	private static final UriMatcher uriMatcher; 
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "books",   BOOKS);
		uriMatcher.addURI(AUTHORITY, "books/#", BOOKS_ID);
	}
	
    private ArrayList<Book> books;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return uri.toString();
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		Author martin = new Author("George R.R. Martin");
		Author tolkien = new Author("J.R.R. Tolkien");
		books = new ArrayList<Book>();
        books.add( new Book("Game of Thrones", martin) );
        books.add( new Book("Clash of Kings", martin) );
        books.add( new Book("Storm of Swords", martin) );
        books.add( new Book("Feast for Crows", martin) );
        books.add( new Book("Dance with Dragons", martin) );
        books.add( new Book("The Lord of the Rings", tolkien) );
        books.add( new Book("The Hobbit", tolkien) );
        books.add( new Book("Silmarillion", tolkien) );
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
