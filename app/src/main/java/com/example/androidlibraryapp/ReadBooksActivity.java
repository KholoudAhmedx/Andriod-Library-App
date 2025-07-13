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

public class ReadBooksActivity extends AppCompatActivity {

    RecyclerView readBooksRecyView;

    // To navigate the user back to main activity rather than to the Book Activity when clicking on the back button

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReadBooksActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // To clear the stack where activities used are saved
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        readBooksRecyView = findViewById(R.id.readBooksRecyView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        readBooksRecyView.setAdapter(adapter);
        readBooksRecyView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance().getAlreadyReadBooks());




    }
}