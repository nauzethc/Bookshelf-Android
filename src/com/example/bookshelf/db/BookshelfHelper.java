package com.example.bookshelf.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class BookshelfHelper extends SQLiteOpenHelper {
	
	private static final String TAG = "com.example.bookshelf.db";
	private static final int version = 1;
	private static final String NAME = "bookshelf.db";
	private static CursorFactory factory;
	
	
	/* ***********************************************************************
	 * TABLE authors
	 */
	public static final class AUTHORS implements BaseColumns {
		private AUTHORS(){}
		// Table name
		public static final String TABLE_NAME = "authors";
		// Columns
		public static final String KEY_ID = "_id";
		public static final String KEY_NAME = "name";
		// Index
		public static final String INDEX_NAME = "authors_name";
	}
	
	/* ***********************************************************************
	 * TABLE books
	 */
	public static final class BOOKS implements BaseColumns {
		private BOOKS(){}
		// Table name
		public static final String TABLE_NAME = "books";
		// Columns
		public static final String KEY_ID = "_id";
		public static final String KEY_TITLE = "title";
		public static final String KEY_YEAR = "year";
		public static final String KEY_AUTHOR = "author";
        // Index
        public static final String INDEX_TITLE = "books_title";
	}	
	

	public BookshelfHelper(Context context) {
		super(context, NAME, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// Authors table
		db.execSQL("CREATE TABLE "+ AUTHORS.TABLE_NAME +"( " +
			AUTHORS.KEY_ID + " INTEGER UNIQUE PRIMARY KEY," +
			AUTHORS.KEY_NAME + " TEXT NOT NULL )");
		// Authors index
		db.execSQL("CREATE UNIQUE INDEX " + 
			AUTHORS.INDEX_NAME + " ON " + 
			AUTHORS.TABLE_NAME + "(" + AUTHORS.KEY_NAME + " ASC)");
		// Fill table
		db.execSQL("INSERT INTO "+ AUTHORS.TABLE_NAME +" VALUES(1, 'George R.R. Martin')");
		db.execSQL("INSERT INTO "+ AUTHORS.TABLE_NAME +" VALUES(2, 'J.R.R. Tolkien')");
		
		// Books table
		db.execSQL("CREATE TABLE "+ BOOKS.TABLE_NAME +"( " +
			BOOKS.KEY_ID + " INTEGER UNIQUE PRIMARY KEY," +
            BOOKS.KEY_TITLE + " title TEXT NOT NULL," +
            BOOKS.KEY_YEAR + " INTEGER," +
            BOOKS.KEY_AUTHOR + " INTEGER NOT NULL )");
        // Books index
        db.execSQL("CREATE UNIQUE INDEX " +
            BOOKS.INDEX_TITLE + " ON " +
            BOOKS.TABLE_NAME + "(" + BOOKS.KEY_TITLE + " ASC)");
		// Fill table
		db.execSQL("INSERT INTO books VALUES(1, 'Game of Thrones', 1996, 1)");
		db.execSQL("INSERT INTO books VALUES(2, 'Clash of Kings', 1996, 1)");
		db.execSQL("INSERT INTO books VALUES(3, 'Storm of Swords', 1996, 1)");
		db.execSQL("INSERT INTO books VALUES(4, 'Feast for Crows', 1996, 1)");
		db.execSQL("INSERT INTO books VALUES(5, 'Dance with Dragons', 2012, 1)");
        db.execSQL("INSERT INTO books VALUES(6, 'The Lord of the Rings', 2012, 2)");
        db.execSQL("INSERT INTO books VALUES(7, 'The Hobbit', 2012, 2)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
            db.execSQL("DELETE TABLE IF EXISTS authors");
            db.execSQL("DELETE TABLE IF EXISTS books");
            onCreate(db);
		}
	}

}
