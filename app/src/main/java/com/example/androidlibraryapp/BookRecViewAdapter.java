package com.example.androidlibraryapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder>{

    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public BookRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bookName.setText(books.get(position).getName());
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.bookImage);

        holder.parentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, books.get(position).getName()+"Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("bookId", books.get(position).getId()); // We need to pass the id of each book to display it when each book is clicked.
                context.startActivity(intent);
            }
        });

        // Check on expanded layout
        if(books.get(position).isExpaned())
        {
            TransitionManager.beginDelayedTransition(holder.parentCardView);
            holder.exandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            // After expansion, check if delete button is visible in all activities except for AllBookActivity
            if(parentActivity.equals("AllBookActivity"))
            {
                // Disable
                holder.btnDelete.setVisibility(View.GONE);

            }
            else if (parentActivity.equals("CurrentlyReadingBookActivity")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() +" book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Delete book
                                if(Utils.getInstance().removeBookFromCurrentlyReading(books.get(position)))
                                {
                                    Toast.makeText(context, "Books is removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();


                    }
                });

            }
            else if (parentActivity.equals("WantToReadBookActivity")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() +" book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Delete book
                                if(Utils.getInstance().removeBookFromWantToRead(books.get(position)))
                                {
                                    Toast.makeText(context, "Books is removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();


                    }
                });

            }
            else if (parentActivity.equals("ReadBookActivity")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() +" book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Delete book
                                if(Utils.getInstance().removeBookFromRead(books.get(position)))
                                {
                                    Toast.makeText(context, "Books is removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();


                    }
                });

            }
            else if (parentActivity.equals("FavouriteBookActivity")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() +" book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Delete book
                                if(Utils.getInstance().removeBookFromFavourites(books.get(position)))
                                {
                                    Toast.makeText(context, "Books is removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();

                    }
                });

            }
        }
        else
        {
            TransitionManager.beginDelayedTransition(holder.parentCardView);
            holder.exandedRelativeLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);

        }

        // Change text
        holder.shortDesc.setText(books.get(position).getShortDesc());
        holder.textAuthor.setText(books.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    // Implement ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parentCardView;
        private TextView bookName, textAuthor, shortDesc, btnDelete;
        private ImageView bookImage, downArrow, upArrow;

        private RelativeLayout exandedRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentCardView = itemView.findViewById(R.id.parentCardView);
            bookName = itemView.findViewById(R.id.txtViewBookName);
            bookImage = itemView.findViewById(R.id.bookImage);
            textAuthor = itemView.findViewById(R.id.textAuthor);
            shortDesc = itemView.findViewById(R.id.shortDesc);
            downArrow = itemView.findViewById(R.id.arrowDownImg);
            upArrow = itemView.findViewById(R.id.arrowUpImg);
            exandedRelativeLayout = itemView.findViewById(R.id.expandedRelativeLayout);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            // Add fucntionality on downArrow to exand
            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // We want to access isExpaned attribute
                    Book book = books.get(getAdapterPosition()); // will get the book we are on.
                    book.setExpaned(!book.isExpaned());
                    notifyItemChanged(getAdapterPosition());
                }

            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition()); // will get the book we are on.
                    book.setExpaned(!book.isExpaned());
                    notifyItemChanged(getAdapterPosition());

                }
            });


        }
    }
}
