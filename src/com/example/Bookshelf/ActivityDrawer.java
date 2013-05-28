package com.example.Bookshelf;

import android.app.ActionBar;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.Bookshelf.fragments.FilterableListFragment;
import com.example.Bookshelf.fragments.FragmentAuthors;
import com.example.Bookshelf.fragments.FragmentBooks;
import com.example.Bookshelf.fragments.FragmentDrawerSlide;

public class ActivityDrawer extends FragmentActivity implements 
	OnItemClickListener,
	SearchView.OnCloseListener,
	SearchView.OnQueryTextListener {

	// ActionBar
	private ActionBar actionBar;
	private ActionBarDrawerToggle drawerToggle;
	// Drawer
	private DrawerLayout drawerLayout;
	// Fragments
    private FragmentManager fragmentManager;
    private FragmentDrawerSlide fragmentDrawer;
    private FragmentBooks fragmentBooks;
    private FragmentAuthors fragmentAuthors;
    private Fragment fragmentToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        
        // Load action bar
        actionBar = getActionBar();
        // Load drawer and toggle
        drawerLayout = (DrawerLayout) findViewById(R.id.ActivityDrawer_Drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
        		R.drawable.ic_drawer,
        		R.string.ActivityDrawer_Drawer_TitleOpened,
        		R.string.ActivityDrawer_Drawer_TitleClosed) {
        	
        	@Override
        	public void onDrawerClosed(View drawerView) {
        		actionBar.setTitle(R.string.ActivityDrawer_Drawer_TitleClosed);
        		invalidateOptionsMenu();
        	}
        	
        	@Override
			public void onDrawerOpened(View drawerView) {
        		actionBar.setTitle(R.string.ActivityDrawer_Drawer_TitleOpened);
        		invalidateOptionsMenu();
			}
        };
        // Set home button as toggle for drawer
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        
        // Load fragments
        fragmentDrawer = new FragmentDrawerSlide();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.ActivityDrawer_Drawer_FrameSlide, fragmentDrawer)
                .commit();
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
    	drawerToggle.syncState();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (drawerToggle.onOptionsItemSelected(item) ||
    		item.getItemId() == android.R.id.home) {
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_drawer, menu);
    	
    	// Set search listener
    	SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    	searchView.setOnQueryTextListener(this);
    	searchView.setOnCloseListener(this);
    	
    	/*
    	// Implement close listener through MenuItem,
    	// it doesn't work with SearchView.OnCloseListener)
    	MenuItem searchItem = (MenuItem) menu.findItem(R.id.action_search);
    	searchItem.setOnActionExpandListener(new OnActionExpandListener() {
			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				return true;
			}			
			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				onQueryTextChange(null);
				return true;
			}
		});
		*/
    	return true;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0: // Authors
			if (fragmentAuthors == null) fragmentAuthors = new FragmentAuthors();
			fragmentToShow = fragmentAuthors;
			break;
		case 1: // Books
			if (fragmentBooks == null) fragmentBooks = new FragmentBooks();
			fragmentToShow = fragmentBooks;
			break;

		default:
			fragmentToShow = null;
			Toast.makeText(getApplication(), "Option selected not valid", Toast.LENGTH_SHORT).show();
			break;
		}
		
		if (fragmentToShow != null) {
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.replace(R.id.ActivityDrawer_Drawer_FrameMain, fragmentToShow);
			ft.commit();
		}
		drawerLayout.closeDrawers();
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (fragmentToShow != null) {
			try {
				FilterableListFragment fragment = (FilterableListFragment) fragmentToShow;
				fragment.setListFilter(newText);
				return true;
			} catch (Exception e) {}
		}
		return false;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		return onQueryTextChange(query);
	}
	@Override
	public boolean onClose() {
		Toast.makeText(getApplicationContext(), "Closed", Toast.LENGTH_SHORT).show();
		return onQueryTextChange(null);
	}
}
