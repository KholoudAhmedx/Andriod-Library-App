package com.example.androidlibraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {


    private ImageView bookImage;
    private Button AddToCurrentlyReading, AddToWantToRead, AddToRead, AddToFavourites;
    private TextView BookNameValue, BookAuthorValue, NoOfPagesValue, ShortDescValue, LongDescValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        InitValues();

        // Get the Id of the book passed, to be displayed
        Intent intent = getIntent();
        if(intent != null)
        {
            int bookId = intent.getIntExtra("bookId", -1);
            if(bookId != -1)
            {
                Book incomingBook = Utils.getInstance().ReturnBookBasedOnID(bookId);
                if(incomingBook != null)
                {
                    setBookData(incomingBook);
                    handleReadBooks(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);

                }

            }
        }

    }

    private void InitValues() {
        // Initialize
        bookImage = findViewById(R.id.bookImage2);
        AddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        AddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        AddToRead = findViewById(R.id.btnAddToRead);
        AddToFavourites = findViewById(R.id.btnAddToFavourites);

        BookNameValue = findViewById(R.id.txtViewBookNameValue);
        BookAuthorValue = findViewById(R.id.txtViewBookAuthorValue);
        NoOfPagesValue = findViewById(R.id.txtViewNoPagesvalue);
        LongDescValue = findViewById(R.id.txtViewLongDescValue);
    }

    private void setBookData(Book book) {
        BookNameValue.setText(book.getName());
        BookAuthorValue.setText(book.getAuthor());
        NoOfPagesValue.setText(String.valueOf(book.getPages())); // Returns String representation of the specified value
        LongDescValue.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);

    }

    /**
     Adding buttons functionalities
     When Clicking on a button, we need to check the following:
     - If the book already in the list, then disable the button
     - If not, add it to the list and navigate the user to the list/page of the books**/

    private void handleReadBooks(final Book book){
        ArrayList<Book> readBooksList = Utils.getInstance().getAlreadyReadBooks();
        boolean exsitAlreayInReadBooksList = false;

        if(readBooksList !=null){
            for(Book b: readBooksList)
            {
                // If book exists, then disable button
                if(b.getId()== book.getId())
                {
                    exsitAlreayInReadBooksList = true;
                }
            }

        }

        if(exsitAlreayInReadBooksList)  { AddToRead.setEnabled(false); }
        else {

            // Add it to the list

            AddToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ClickTest", "Button clicked!");
                    if(Utils.getInstance().AddBookToReadBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added to ReadList", Toast.LENGTH_SHORT).show();
                        //Navigate the user to Read books activity
                        Intent intent = new Intent(BookActivity.this, ReadBooksActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Book is not added, try again later.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(Book book){
        ArrayList<Book> wantToReadBooksList = Utils.getInstance().getWantToReadBooks();
        boolean existInWantToReadList = false;

        for(Book b: wantToReadBooksList) {
            if (b.getId() == book.getId()) {
                existInWantToReadList = true;

            }
        }
        if(existInWantToReadList)
        {
            //If book already exist in this list(marked as want to read)
            AddToWantToRead.setEnabled(false);
        }
        else {

            // Add it to list
            AddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().AddBookToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added to list", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadBooksActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Book was not added, Try again later.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    private void handleCurrentlyReadingBooks(Book book){

    }

    private void handleFavouriteBooks(Book book){

    }

}