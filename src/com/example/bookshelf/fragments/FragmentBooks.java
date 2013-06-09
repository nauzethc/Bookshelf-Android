package com.example.bookshelf.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.example.bookshelf.R;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.db.BookshelfHelper;
import com.example.bookshelf.providers.BookshelfProvider;

public class FragmentBooks extends ListFragment implements
	//SearchView.OnQueryTextListener,
	//SearchView.OnCloseListener,
    LoaderManager.LoaderCallbacks<Cursor> {

	private SearchView searchView;
	private MenuItem searchItem;
    private SimpleCursorAdapter bookAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fill with adapter
        //setEmptyText("No books");
        setHasOptionsMenu(true);

        bookAdapter = new SimpleCursorAdapter(getActivity(),
            android.R.layout.simple_list_item_2,
            null,
            new String[] { BookshelfHelper.BOOKS.KEY_TITLE, BookshelfHelper.BOOKS.KEY_YEAR },
            new int[] { android.R.id.text1, android.R.id.text2 },
            0);

        setListAdapter(bookAdapter);
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
	*/

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri = Uri.parse(BookshelfProvider.URI + "books");
        return new CursorLoader(getActivity(), uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        bookAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        bookAdapter.swapCursor(null);
    }
}
