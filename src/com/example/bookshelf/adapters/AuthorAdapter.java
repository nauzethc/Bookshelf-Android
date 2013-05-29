package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.bookshelf.R;
import com.example.bookshelf.models.Author;
import com.example.bookshelf.providers.AuthorProvider;

import java.util.ArrayList;

public class AuthorAdapter extends BaseAdapter implements Filterable {

    private AuthorFilter filter;
    private final ArrayList<Author> authors;
    private ArrayList<Author> authorsFiltered;
    private Context context;

    public AuthorAdapter(Context context) {
        this.context = context;
        // Load authors
        authors = AuthorProvider.getAuthors();
        authorsFiltered = new ArrayList<Author>(authors);
    }

    @Override
    public int getCount() {
        return authorsFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return authorsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public TextView authorName;
        public TextView authorBooksCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View authorView = convertView;
        if (authorView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            authorView = inflater.inflate(R.layout.fragment_authors_list_view, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.authorName = (TextView) authorView.findViewById(R.id.AuthorView_Text_Name);
            holder.authorBooksCount = (TextView) authorView.findViewById(R.id.AuthorView_Text_Books);
            authorView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) authorView.getTag();
        Author author = (Author) getItem(position);
        holder.authorName.setText(author.name);
        holder.authorBooksCount.setText(Integer.toString(author.booksCount)+" book(s)");
        return authorView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
        	filter = new AuthorFilter();
        }
        return filter;
    }

    private class AuthorFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
        	FilterResults filterResults = new FilterResults();
        	
        	if (constraint == null || constraint.length() == 0) {
        		filterResults.values = new ArrayList<Author>(authors);
        		filterResults.count = authors.size();
        	} else {
        		ArrayList<Author> newAuthors = new ArrayList<Author>();
        		for (Author author: authors) {
        			if (author.name.toLowerCase().trim().contains( constraint.toString().toLowerCase() )) {
        				newAuthors.add(author);
        			}
        		}
        		filterResults.values = newAuthors;
        		filterResults.count = newAuthors.size();
        	}
        	return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        	authorsFiltered = (ArrayList<Author>) results.values;
        	notifyDataSetChanged();
        }
    }
}
