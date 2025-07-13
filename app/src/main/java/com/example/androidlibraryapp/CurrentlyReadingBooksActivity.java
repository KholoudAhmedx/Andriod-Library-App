package com.example.androidlibraryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CurrentlyReadingBooksActivity extends AppCompatActivity {

    RecyclerView currentlyReadBooksRecycView;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CurrentlyReadingBooksActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // To clear the stack where activities used are saved
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_currently_reading_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentlyReadBooksRecycView = findViewById(R.id.currentlyReadingBooksRecyView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        currentlyReadBooksRecycView.setAdapter(adapter);
        currentlyReadBooksRecycView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance().getCurrentlyReadingBooks());
    }
}