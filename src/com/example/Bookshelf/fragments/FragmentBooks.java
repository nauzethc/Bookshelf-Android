package com.example.Bookshelf.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;


import com.example.Bookshelf.adapters.BookAdapter;

public class FragmentBooks extends FilterableListFragment {

    private BookAdapter bookAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookAdapter = new BookAdapter(getActivity());
        setListAdapter(bookAdapter);
    }

	@Override
	public void setListFilter(String text) {
		bookAdapter.getFilter().filter(text);
	}
}
