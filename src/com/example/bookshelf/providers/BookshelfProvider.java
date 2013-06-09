package com.example.bookshelf.providers;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.bookshelf.db.BookshelfHelper;

public class BookshelfProvider extends ContentProvider {
    
	// URI
	public static final String AUTHORITY   = "com.example.bookshelf";
	public static final String URI         = "content://"+ AUTHORITY +"/";
	public static final Uri    CONTENT_URI = Uri.parse(URI);
	
	// Database helper
	private BookshelfHelper bookshelfHelper;

    // URIs
    public static final int URI_AUTHORS = 101;
    public static final int URI_AUTHORS_ID = 102;
    public static final int URI_BOOKS = 201;
    public static final int URI_BOOKS_ID = 202;
	
	/* ***********************************************************************
	 * URI Matcher
	 */
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "authors",   URI_AUTHORS);
		uriMatcher.addURI(AUTHORITY, "authors/#", URI_AUTHORS_ID);
		uriMatcher.addURI(AUTHORITY, "books",     URI_BOOKS);
		uriMatcher.addURI(AUTHORITY, "books/#",   URI_BOOKS_ID);
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case URI_AUTHORS:
			return "vnd.android.cursor.dir/vnd.bookshelf.author";
		case URI_AUTHORS_ID:
			return "vnd.android.cursor.item/vnd.bookshelf.author";
		case URI_BOOKS:
			return "vnd.android.cursor.dir/vnd.bookshelf.book";
		case URI_BOOKS_ID:
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
		case URI_AUTHORS:
			cursor = db.query(BookshelfHelper.AUTHORS.TABLE_NAME,
					projection,
					selection, 
					selectionArgs,
					null,
					null,
					sortOrder);
			return cursor;
		
		// Query Author by ID
		case URI_AUTHORS_ID:
			cursor = db.query(BookshelfHelper.AUTHORS.TABLE_NAME,
					projection,
					BookshelfHelper.AUTHORS.KEY_ID +"="+ uri.getLastPathSegment(),
					selectionArgs,
					null,
					null,
					sortOrder);
			return cursor;
		
		// Query all Books
		case URI_BOOKS:
			cursor = db.query(BookshelfHelper.BOOKS.TABLE_NAME,
					projection,
					selection, 
					selectionArgs,
					null,
					null,
					sortOrder);
			return cursor;
		
		// Query Book by ID
		case URI_BOOKS_ID:
			cursor = db.query(BookshelfHelper.BOOKS.TABLE_NAME,
					projection,
					BookshelfHelper.BOOKS.KEY_ID +"="+ uri.getLastPathSegment(),
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
