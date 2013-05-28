package com.example.Bookshelf.fragments;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import com.example.Bookshelf.ActivityDrawer;

public class FragmentDrawerSlide extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Load options on list
        ArrayList<String> values = new ArrayList<String>();
        values.add("Authors");
        values.add("Books");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                values);
        setListAdapter(adapter);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onViewCreated(view, savedInstanceState);
    	getListView().setOnItemClickListener((ActivityDrawer)getActivity());
    }
}
