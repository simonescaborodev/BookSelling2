package com.simonescaboro.bookselling.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simonescaboro.bookselling.R;
import com.simonescaboro.bookselling.item.Book;
import com.simonescaboro.bookselling.item.BookList;

import java.util.ArrayList;
import java.util.List;

public class AllBooks extends AppCompatActivity {


    DatabaseReference databaseBooks;
    ListView listViewBooks;
    List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_books_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseBooks = FirebaseDatabase.getInstance().getReference("books");

        listViewBooks = (ListView) findViewById(R.id.listViewBooks);

        bookList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), AddNewBook.class));
            }
        });
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        String added = intent.getStringExtra(AddNewBook.ITEM_ADDED);
            String title = intent.getStringExtra(AddNewBook.ITEM_TITLE);
            String author = intent.getStringExtra(AddNewBook.ITEM_AUTHOR);
            String seller = intent.getStringExtra(AddNewBook.ITEM_SELLER);
            String id = databaseBooks.push().getKey();
            Book book = new Book(title,author,seller,id);
            databaseBooks.child(id).setValue(book);


    }
*/


    @Override
    protected void onStart() {
        super.onStart();
        databaseBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bookList.clear();

                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    Book book = bookSnapshot.getValue(Book.class);
                    bookList.add(book);
                }

                BookList adapter = new BookList(AllBooks.this, bookList);
                listViewBooks.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}