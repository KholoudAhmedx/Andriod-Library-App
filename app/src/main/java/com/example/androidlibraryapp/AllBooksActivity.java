package com.example.androidlibraryapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    RecyclerView booksRecView;
    BookRecViewAdapter  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the recycler view

        // Create an adapter
        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, 499, "Lost in Mecca", "https://m.media-amazon.com/images/I/51Imj-JDfKL._SL1500_.jpg",
                "Bothayna Elessa", "About loss and the search for meaning.", "Long Description"));
        books.add(new Book(2, 400, "The Apartment Called Freedom", "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1525026933i/1652330.jpg",
                "Ghazi A. Algosaibi", "The novel is set between 1956 and 1961, and tells of the experiences in love and politics of four Bahraini young men studying at university in Cairo.", "Long Description"));

        adapter.setBooks(books);

    }
}