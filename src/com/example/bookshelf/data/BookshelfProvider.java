package com.example.bookshelf.data;


import android.content.ContentProvider;


public abstract class BookshelfProvider extends ContentProvider {
    
	// AUTHORITY
	public static final String AUTHORITY = "com.example.bookshelf";
	
	// Database helper
	BookshelfHelper bookshelfHelper;
	
	// Initialize helper on create
	@Override
	public boolean onCreate() {
		bookshelfHelper = new BookshelfHelper(getContext());
		return true;
	}
}
