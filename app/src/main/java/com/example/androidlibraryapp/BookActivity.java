package com.example.androidlibraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

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
}