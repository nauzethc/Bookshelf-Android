package com.example.Bookshelf.fragments;

import com.example.Bookshelf.adapters.AuthorAdapter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class FragmentAuthors extends FilterableListFragment {
	
	private AuthorAdapter authorAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		authorAdapter = new AuthorAdapter(getActivity());
		setListAdapter(authorAdapter);
	}

	@Override
	public void setListFilter(String text) {
		authorAdapter.getFilter().filter(text);
	}

}
