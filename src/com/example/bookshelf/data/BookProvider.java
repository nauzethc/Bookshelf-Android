package com.example.bookshelf.data;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class BookProvider extends BookshelfProvider {
	
	// Public content URI
	public static final String URI = "content://"+ AUTHORITY +"/authors";
	public static final Uri CONTENT_URI = Uri.parse(URI);
	
	// URIs
    public static final int URI_BOOKS = 101;
    public static final int URI_BOOKS_ID = 102;
    
    /* ***********************************************************************
	 * URI Matcher
	 */
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "books",   URI_BOOKS);
		uriMatcher.addURI(AUTHORITY, "books/#", URI_BOOKS_ID);
	}
	
	/* ***********************************************************************
	 * MIME Types
	 */
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case URI_BOOKS:
			return "vnd.android.cursor.dir/vnd.bookshelf.book";
		case URI_BOOKS_ID:
			return "vnd.android.cursor.item/vnd.bookshelf.book";
		default:
			return null;
		}
	}
	
	/* ***********************************************************************
	 * Queries
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteDatabase db = bookshelfHelper.getReadableDatabase();
		Cursor cursor;
		switch (uriMatcher.match(uri)) {
		
		// Query all Books, join authors
		case URI_BOOKS:
			cursor = db.rawQuery(
				"SELECT " +
				BookshelfHelper.BOOKS.TABLE_NAME+"."+BookshelfHelper.BOOKS.KEY_ID + ", " +
				BookshelfHelper.BOOKS.TABLE_NAME+"."+BookshelfHelper.BOOKS.KEY_TITLE + ", " +
				BookshelfHelper.BOOKS.TABLE_NAME+"."+BookshelfHelper.BOOKS.KEY_YEAR + ", " +
				BookshelfHelper.AUTHORS.TABLE_NAME+"."+BookshelfHelper.AUTHORS.KEY_NAME + " AS author " +
				"FROM " + 
				BookshelfHelper.BOOKS.TABLE_NAME + " " +
				"LEFT OUTER JOIN " + BookshelfHelper.AUTHORS.TABLE_NAME + " ON " +
				BookshelfHelper.BOOKS.TABLE_NAME+"."+BookshelfHelper.BOOKS.KEY_AUTHOR + "=" + 
				BookshelfHelper.AUTHORS.TABLE_NAME+"."+BookshelfHelper.AUTHORS.KEY_ID,
				selectionArgs);
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
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
