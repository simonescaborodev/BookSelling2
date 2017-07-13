package com.simonescaboro.bookselling.item;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.simonescaboro.bookselling.R;

import java.util.List;

/**
 * Created by Simone on 10/07/2017.
 */

public class BookList extends ArrayAdapter<Book> {
    private Activity context;
    private List<Book> booksList;

    public BookList(Activity context, List<Book> booksList){
        super(context, R.layout.item_layout, booksList);
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.item_layout, null, true);

        TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.textViewAuthor);
        TextView textViewSeller = (TextView) listViewItem.findViewById(R.id.textViewSeller);
        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);

        Book book = booksList.get(position);

        textViewAuthor.setText(book.getBookAuthor());
        textViewSeller.setText(book.getBookSeller());
        textViewTitle.setText(book.getBookTitle());

        return listViewItem;
    }
}
