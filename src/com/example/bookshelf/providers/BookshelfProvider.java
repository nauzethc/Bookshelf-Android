package com.example.bookshelf.providers;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.bookshelf.db.BookshelfHelper;
import com.example.bookshelf.models.Author;
import com.example.bookshelf.models.Book;

import java.util.ArrayList;

public class BookshelfProvider extends ContentProvider {
    
	// URI
	public static final String AUTHORITY   = "com.example.bookshelf";
	public static final String URI         = "content://"+ AUTHORITY +"/";
	public static final Uri    CONTENT_URI = Uri.parse(URI);
	
	// Database helper
	private BookshelfHelper bookshelfHelper;
	
	
	/* ***********************************************************************
	 * TABLE authors
	 */
	public static final class AUTHORS implements BaseColumns {
		private AUTHORS(){}
		// Table name
		public static final String NAME = "authors";
		// Columns
		public static final String KEY_ID = "_id";
		public static final String KEY_NAME = "name";
		// URIs
		public static final int URI_AUTHORS = 101;
		public static final int URI_AUTHORS_ID = 102;
	}	
	
	/* ***********************************************************************
	 * TABLE books
	 */
	public static final class BOOKS implements BaseColumns {
		private BOOKS(){}
		// Table name
		public static final String NAME = "books";
		// Columns
		public static final String KEY_ID = "_id";
		public static final String KEY_TITLE = "title";
		public static final String KEY_YEAR = "year";
		public static final String KEY_AUTHOR = "author";
		// URIs
		private static final int URI_BOOKS = 201;
		private static final int URI_BOOKS_ID = 202;
	}	
	
	/* ***********************************************************************
	 * URI Matcher
	 */
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "authors",   AUTHORS.URI_AUTHORS);
		uriMatcher.addURI(AUTHORITY, "authors/#", AUTHORS.URI_AUTHORS_ID);
		uriMatcher.addURI(AUTHORITY, "books",     BOOKS.URI_BOOKS);
		uriMatcher.addURI(AUTHORITY, "books/#",   BOOKS.URI_BOOKS_ID);
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case AUTHORS.URI_AUTHORS:
			return "vnd.android.cursor.dir/vnd.bookshelf.author";
		case AUTHORS.URI_AUTHORS_ID:
			return "vnd.android.cursor.item/vnd.bookshelf.author";
		case BOOKS.URI_BOOKS:
			return "vnd.android.cursor.dir/vnd.bookshelf.book";
		case BOOKS.URI_BOOKS_ID:
			return "vnd.android.cursor.item/vnd.bookshelf.book";
		default:
			return null;
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		bookshelfHelper = new BookshelfHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteDatabase db = bookshelfHelper.getReadableDatabase();
		Cursor cursor;
		switch (uriMatcher.match(uri)) {
		
		// Query all Authors
		case AUTHORS.URI_AUTHORS:
			cursor = db.query(AUTHORS.NAME,
					projection,
					selection, 
					selectionArgs,
					null,
					null,
					sortOrder);
			return cursor;
		
		// Query Author by ID
		case AUTHORS.URI_AUTHORS_ID:
			cursor = db.query(AUTHORS.NAME,
					projection,
					AUTHORS.KEY_ID +"="+ uri.getLastPathSegment(), 
					selectionArgs,
					null,
					null,
					sortOrder);
			return cursor;
		
		// Query all Books
		case BOOKS.URI_BOOKS:
			cursor = db.query(BOOKS.NAME,
					projection,
					selection, 
					selectionArgs,
					null,
					null,
					sortOrder);
			return cursor;
		
		// Query Book by ID
		case BOOKS.URI_BOOKS_ID:
			cursor = db.query(BOOKS.NAME,
					projection,
					BOOKS.KEY_ID +"="+ uri.getLastPathSegment(),
					selectionArgs,
					null,
					null,
					sortOrder);
			return cursor;

		default:
			return null;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
