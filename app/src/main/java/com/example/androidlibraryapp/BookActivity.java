package com.example.androidlibraryapp;

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

        //TODO: GET the data of each book clicked
        String longDescription = "In August 1956, 16-year-old Fouad, a young Bahraini man, packs to go to Cairo, where he will study law. "
                + "He and his Bahraini peers are supporters of Egyptian President Gamal Abdel Nasser.\n\n"
                + "Upon arriving in Cairo, Fouad faces difficulty in finding a visa and finding his friends. Mr. Shareef, his former teacher, "
                + "returns from a trip and helps Fouad with both logistical matters and as a mentor. Mr. Shareef helps Fouad find a boarding house, "
                + "where he stays with a Jordanian man, Adnan, an Iraqi man, Majeed, and his friend Qasim. His two other Bahraini friends, Yacoub and "
                + "Abdul-Karim, lodge elsewhere. The six men soon become close friends.\n\n"
                + "The Bahraini men are surprised by the comparative liberalism of Egypt, especially when it comes to women, and they quickly become "
                + "interested in finding girlfriends. They soon manage to rent their own apartment, which they call Apartment Freedom. They set up 70 rules "
                + "for themselves and any other roommates. Their quests to find girlfriends leads some of them to engage with sex workers, while Fouad finds "
                + "a number of girlfriends. Fouad considers marrying one of the women he dates, but his parents reject the idea, having already started to "
                + "arrange a marriage for him.\n\n"
                + "The men also engage in politicsal movements. Fouad joins the Arab Nationalist Movement, while some of his roommates join the Muslim Brotherhood "
                + "or the Communist party. They also discuss the politics amongst themselves. The group discover some splits among religious lines, as one of the "
                + "roommates is Shia while the rest are Sunni.\n\n"
                + "The book also contains some stories written by Fouad and his friend Abdul-Ra'ouf. The two eventually publish their stories in a moderately-successful "
                + "book, leading them to meet literary and political figures.\n\n"
                + "The book ends in 1961.";

        Book book = new Book(2, 400, "The Apartment Called Freedom", "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1525026933i/1652330.jpg",
                "Ghazi A. Algosaibi", "The novel is set between 1956 and 1961, and tells of the experiences in love and politics of four Bahraini young men studying at university in Cairo.", longDescription);

        setBookData(book);
    }

    private void InitValues()
    {
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

    private void setBookData(Book book)
    {
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