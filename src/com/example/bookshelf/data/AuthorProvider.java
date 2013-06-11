package com.example.bookshelf.data;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class AuthorProvider extends BookshelfProvider {
	
	// Public content URI
	public static final String URI = "content://"+ AUTHORITY +"/books";
	public static final Uri CONTENT_URI = Uri.parse(URI);
	
	// URIs
	public static final int URI_AUTHORS = 101;
    public static final int URI_AUTHORS_ID = 102;
    
    /* ***********************************************************************
	 * URI Matcher
	 */
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "books",   URI_AUTHORS);
		uriMatcher.addURI(AUTHORITY, "books/#", URI_AUTHORS_ID);
	}
	
	/* ***********************************************************************
	 * MIME Types
	 */
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case URI_AUTHORS:
			return "vnd.android.cursor.dir/vnd.bookshelf.author";
		case URI_AUTHORS_ID:
			return "vnd.android.cursor.item/vnd.bookshelf.author";
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
		
		// Query all Authors
		case URI_AUTHORS:
			cursor = db.rawQuery(
				"SELECT " +
				BookshelfHelper.AUTHORS.TABLE_NAME+"."+BookshelfHelper.AUTHORS.KEY_ID + ", " +
				BookshelfHelper.AUTHORS.TABLE_NAME+"."+BookshelfHelper.AUTHORS.KEY_NAME + ", " +
					"(SELECT COUNT(*) FROM " +
					BookshelfHelper.BOOKS.TABLE_NAME + " " +
					"WHERE " +
					BookshelfHelper.BOOKS.TABLE_NAME+"."+BookshelfHelper.BOOKS.KEY_AUTHOR + "=" +
					BookshelfHelper.AUTHORS.TABLE_NAME+"."+BookshelfHelper.AUTHORS.KEY_ID + ") AS books " +
				"FROM " +
				BookshelfHelper.AUTHORS.TABLE_NAME,
				selectionArgs);
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
