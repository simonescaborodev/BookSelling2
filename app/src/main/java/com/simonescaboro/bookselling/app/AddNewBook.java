package com.simonescaboro.bookselling.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simonescaboro.bookselling.R;
import com.simonescaboro.bookselling.item.Book;

public class AddNewBook extends AppCompatActivity {

    private EditText bookTitle;
    private EditText bookAuthor;
    private EditText bookEditor;
    private EditText bookEdition;
    private EditText bookPrice;
    private EditText bookIsbn;
    private EditText bookDescription;
    private EditText bookPublishingYear;
    private Spinner bookLanguage;

    private SeekBar bookQuality;

    private Button buttonAdd;
    private Button buttonImage;
    //////////////////////////////////////////////////////
    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    //////////////////////////////////////////////////////
    DatabaseReference databaseBooks;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        auth = FirebaseAuth.getInstance();
        findId();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBook();
            }
        });

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddNewBook.this, "Stai nel tuo! Non puoi ancora farlo!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findId(){
        bookTitle = (EditText) findViewById(R.id.bookTitle);
        bookAuthor = (EditText) findViewById(R.id.bookAuthor);
        bookEditor = (EditText) findViewById(R.id.bookEditor);
        bookEdition = (EditText) findViewById(R.id.bookEdition);
        bookPrice = (EditText) findViewById(R.id.bookPrice);
        bookIsbn = (EditText) findViewById(R.id.bookIsbn);
        bookDescription = (EditText) findViewById(R.id.bookDescription);
        bookPublishingYear = (EditText) findViewById(R.id.bookPublishingYear);
        bookLanguage = (Spinner) findViewById(R.id.bookLanguage);
        bookQuality = (SeekBar) findViewById(R.id.bookQuality);
        buttonAdd = (Button) findViewById(R.id.addButton);
        buttonImage = (Button) findViewById(R.id.addImage);
    }

    private void addBook(){
        String title = bookTitle.getText().toString().trim();
        String author = bookAuthor.getText().toString().trim();
        String editor = bookEditor.getText().toString().trim();
        String edition = bookEdition.getText().toString().trim();
        String price = bookPrice.getText().toString().trim();
        String isbn = bookIsbn.getText().toString().trim();
        String language = bookLanguage.getSelectedItem().toString();
        String description = bookDescription.getText().toString().trim();
        String publishingYear = bookTitle.getText().toString().trim();
        String seller = auth.getCurrentUser().getEmail();
        int quality = bookQuality.getProgress();
        String qual;
        if(quality < 50) {
            qual = "1";
        } else {
            qual = "2";
        }
        //int year = Integer.parseInt(publishingYear.replaceAll("[\\D]", ""));
        int year = 666;
        int edition1 = 666;
        //int edition1 = Integer.parseInt(edition);
        //int edition1 = Integer.parseInt(edition.replaceAll("[\\D]", ""));
        if(!title.isEmpty() && !author.isEmpty() && !price.isEmpty() && !qual.isEmpty()){
            databaseBooks = FirebaseDatabase.getInstance().getReference("books");
            String id = databaseBooks.push().getKey();
            Book book = new Book(id,title,author,editor,description,language,seller,price,isbn,edition1,year,qual);
            displayNotification();
            databaseBooks.child(id).setValue(book);
            startActivity(new Intent(getApplicationContext(),AllBooks.class));
        } else {
            Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
        }

    }

    private void displayNotification(){
        Log.i("Start", "notification"); /* Invoking the default notification service */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("New Message"); mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!"); mBuilder.setSmallIcon(R.drawable.ic_library); /* Increase notification number every time a new notification arrives */
        //mBuilder.setNumber(++numMessages); /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, NotificationView.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class); /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(notificationID, mBuilder.build());
    }


}
