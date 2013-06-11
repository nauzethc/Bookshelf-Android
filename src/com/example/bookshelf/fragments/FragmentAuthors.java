package com.example.bookshelf.fragments;

import com.example.bookshelf.R;
import com.example.bookshelf.adapters.AuthorAdapter;
import com.example.bookshelf.db.BookshelfHelper;
import com.example.bookshelf.providers.BookshelfProvider;

import android.R.anim;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.CursorLoader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class FragmentAuthors extends ListFragment implements 
	//SearchView.OnQueryTextListener,
	//SearchView.OnCloseListener,
	LoaderManager.LoaderCallbacks<Cursor> {
	
	private MenuItem searchItem;
	private SearchView searchView;
	private SimpleCursorAdapter authorAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		// Fill with adapter
		authorAdapter = new SimpleCursorAdapter(getActivity(),
				android.R.layout.simple_list_item_2,
				null,
				new String[] {
					BookshelfHelper.AUTHORS.TABLE_NAME+"."+BookshelfHelper.AUTHORS.KEY_NAME, "books" },
				new int[] { android.R.id.text1, android.R.id.text2 },
				0);
		
		setListAdapter(authorAdapter);
		getActivity().getSupportLoaderManager().initLoader(0, null, this);
	}
	
	/*
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
	*/

	@Override
	public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
		Uri uri = Uri.parse(BookshelfProvider.URI + "authors");
		return new CursorLoader(getActivity(), uri, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		authorAdapter.swapCursor(arg1);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		authorAdapter.swapCursor(null);
	}

}
