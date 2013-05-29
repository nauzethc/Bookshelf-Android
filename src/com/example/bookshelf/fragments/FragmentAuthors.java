package com.example.bookshelf.fragments;

import com.example.bookshelf.R;
import com.example.bookshelf.adapters.AuthorAdapter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

public class FragmentAuthors extends ListFragment implements 
	SearchView.OnQueryTextListener,
	SearchView.OnCloseListener {
	
	private AuthorAdapter authorAdapter;
	private MenuItem searchItem;
	private SearchView searchView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		authorAdapter = new AuthorAdapter(getActivity());
		setListAdapter(authorAdapter);
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
		authorAdapter.getFilter().filter(newText);
		return true;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		authorAdapter.getFilter().filter(query);
		return true;
	}
	@Override
	public boolean onClose() {
		authorAdapter.getFilter().filter(null);
		return true;
	}

}
