package com.example.bookshelf.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.bookshelf.R;
import com.example.bookshelf.adapters.BookAdapter;

public class FragmentBooks extends ListFragment implements
	SearchView.OnQueryTextListener,
	SearchView.OnCloseListener {

	private SearchView searchView;
	private MenuItem searchItem;
    private BookAdapter bookAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookAdapter = new BookAdapter(getActivity());
        setListAdapter(bookAdapter);
        setHasOptionsMenu(true);
    }
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (searchView == null) {
			searchView = new SearchView(getActivity());
			searchView.setIconifiedByDefault(true);
			searchView.setOnQueryTextListener(this);
		}
		searchItem = menu.add(R.string.ActivityDrawer_Menu_Search);
		searchItem.setIcon(android.R.drawable.ic_menu_search);
		searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		searchItem.setActionView(searchView);
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		bookAdapter.getFilter().filter(newText);
		return true;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		bookAdapter.getFilter().filter(query);
		return true;
	}
	@Override
	public boolean onClose() {
		bookAdapter.getFilter().filter(null);
		return true;
	}
}
