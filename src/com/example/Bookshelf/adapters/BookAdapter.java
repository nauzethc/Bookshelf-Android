package com.example.Bookshelf.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.Bookshelf.R;
import com.example.Bookshelf.models.Book;
import com.example.Bookshelf.providers.BookProvider;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter implements Filterable {

    private BookFilter filter;
    private final ArrayList<Book> books;
    private ArrayList<Book> booksFiltered;
    private Context context;

    public BookAdapter(Context context) {
        this.context = context;
        // Load books
        books = BookProvider.getBooks();
        // Replicate to filtered
        booksFiltered = new ArrayList<Book>(books);
    }



    @Override
    public int getCount() {
        return booksFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return booksFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public TextView  bookTitle;
        public TextView  bookAuthor;
        public ImageView bookCover;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View bookView = convertView;
        if (bookView == null) {
            // Load inflater for customizing view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            bookView = inflater.inflate(R.layout.fragment_books_list_view, parent, false);
            // Create new holder
            ViewHolder holder = new ViewHolder();
            holder.bookTitle = (TextView) bookView.findViewById(R.id.BookView_Text_Title);
            holder.bookAuthor = (TextView) bookView.findViewById(R.id.BookView_Text_Author);
            holder.bookCover = (ImageView) bookView.findViewById(R.id.BookView_Image_Cover);
            // Assign tag
            bookView.setTag(holder);
        }

        // Load data into view
        ViewHolder holder = (ViewHolder) bookView.getTag();
        Book book = (Book) getItem(position);
        holder.bookTitle.setText(book.title);
        holder.bookAuthor.setText(book.author.name);
        holder.bookCover.setImageDrawable( context.getResources().getDrawable(R.drawable.ic_launcher) );
        return bookView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new BookFilter();
        }
        return filter;
    }

    private class BookFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                filterResults.values = new ArrayList<Book>(books);
                filterResults.count = books.size();
            } else {
                ArrayList<Book> newBooks = new ArrayList<Book>();
                for (Book book: books) {
                    if (book.title.toLowerCase().trim().contains( constraint.toString().toLowerCase() )) {
                        newBooks.add(book);
                    }
                }
                filterResults.values = newBooks;
                filterResults.count = newBooks.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            booksFiltered = (ArrayList<Book>) results.values;
            notifyDataSetChanged();
        }
    }
}