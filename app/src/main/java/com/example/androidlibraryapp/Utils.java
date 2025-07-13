package com.example.androidlibraryapp;

import java.util.ArrayList;

// Is the corresonding for Databases; Where books data are stored
//TODO: Change it to database later;
public class Utils {
    private static Utils instance;

    // Tables==Arraylists
    private static ArrayList<Book> AllBooks;
    private static ArrayList<Book> CurrentlyReadingBooks;
    private static ArrayList<Book> WantToReadBooks;
    private static ArrayList<Book> AlreadyReadBooks;
    private static ArrayList<Book> FavouriteBooks;

    private Utils(){
        if(AllBooks == null)
        {
            AllBooks = new ArrayList<>();
            initData(); // Take it from the RecyclerView
        }

        if (CurrentlyReadingBooks == null) { CurrentlyReadingBooks = new ArrayList<>();}
        if (WantToReadBooks == null) { WantToReadBooks = new ArrayList<>(); }
        if (AlreadyReadBooks == null) { AlreadyReadBooks = new ArrayList<>(); }
        if (FavouriteBooks == null) { FavouriteBooks = new ArrayList<>();}

    }

    private void initData() {

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

        AllBooks.add(new Book(1, 499, "Lost in Mecca", "https://m.media-amazon.com/images/I/51Imj-JDfKL._SL1500_.jpg",
                "Bothayna Elessa", "About loss and the search for meaning.", "Long Description"));
        AllBooks.add(new Book(2, 400, "The Apartment Called Freedom", "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1525026933i/1652330.jpg",
                "Ghazi A. Algosaibi", "The novel is set between 1956 and 1961, and tells of the experiences in love and politics of four Bahraini young men studying at university in Cairo.", longDescription));

    }
    // Singletone pattern
    public static Utils getInstance() {
        if(null != instance)
        {
            return instance;
        }
        else
        {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() { return AllBooks; }

    public static ArrayList<Book> getCurrentlyReadingBooks() { return CurrentlyReadingBooks; }

    public static ArrayList<Book> getWantToReadBooks() { return WantToReadBooks; }

    public static ArrayList<Book> getAlreadyReadBooks() { return AlreadyReadBooks; }

    public static ArrayList<Book> getFavouriteBooks() { return FavouriteBooks; }

    // Return books based on ID
    public Book ReturnBookBasedOnID(int bookId) {
        for(Book b: AllBooks)
        {
            if(b.getId() == bookId)
            {
                return b;
            }
        }
        return null;
    }

    public boolean AddBookToReadBooks(Book b){
        return AlreadyReadBooks.add(b);
    }

    public  boolean AddBookToWantToRead(Book b ){ return WantToReadBooks.add(b); }

    public boolean AddBookToCurrentlyReading(Book b) { return CurrentlyReadingBooks.add(b); }

    public boolean AddBookToFavouriteBooks(Book b){ return FavouriteBooks.add(b);}
}
