package com.example.bookshelf.fragments;

import android.support.v4.app.ListFragment;

public abstract class FilterableListFragment extends ListFragment {
	
	public abstract void setListFilter(String text);

}