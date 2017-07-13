package com.simonescaboro.bookselling.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simonescaboro.bookselling.R;
import com.simonescaboro.bookselling.item.Book;

public class AddNewBook extends AppCompatActivity {

    public static final String ITEM_ADDED = "added";
    public static final String ITEM_SELLER = "seller";
    public static final String ITEM_AUTHOR = "author";
    public static final String ITEM_TITLE = "title";

    EditText editTextTitle;
    EditText editTextAuthor;
    EditText editTextSeller;
    Button buttonAdd;

    DatabaseReference databaseBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_layout);

        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextAuthor = (EditText) findViewById(R.id.editTextAuthor);
        editTextSeller = (EditText) findViewById(R.id.editTextSeller);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString().trim();
                String author = editTextAuthor.getText().toString().trim();
                String seller = editTextSeller.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(),AllBooks.class);
                if(title.isEmpty() || author.isEmpty() || seller.isEmpty()){
                    databaseBooks = FirebaseDatabase.getInstance().getReference("books");
                    String id = databaseBooks.push().getKey();
                    Book book = new Book(title, author, seller, id);
                    databaseBooks.child(id).setValue(book);
                }
            }
        });
    }
}
