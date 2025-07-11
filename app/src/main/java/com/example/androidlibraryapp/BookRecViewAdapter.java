package com.example.androidlibraryapp;

import android.annotation.SuppressLint;
import android.content.Context;
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

    public BookRecViewAdapter(Context context) {
        this.context = context;
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
                context.startActivity(intent);
            }
        });

        // Check on expanded layout
        if(books.get(position).isExpaned())
        {
            TransitionManager.beginDelayedTransition(holder.parentCardView);
            holder.exandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        }else
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
        private TextView bookName, textAuthor, shortDesc;
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
