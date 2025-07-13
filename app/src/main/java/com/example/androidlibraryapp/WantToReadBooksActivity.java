package com.example.androidlibraryapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.util.Util;

public class WantToReadBooksActivity extends AppCompatActivity {


    RecyclerView wantToReadRecyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_want_to_read_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        wantToReadRecyView = findViewById(R.id.wantToReadRecyViewer);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        wantToReadRecyView.setAdapter(adapter);
        wantToReadRecyView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance().getWantToReadBooks());


    }
}