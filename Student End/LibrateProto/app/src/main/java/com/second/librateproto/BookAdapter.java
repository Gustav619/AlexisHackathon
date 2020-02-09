package com.second.librateproto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    ArrayList<Books> books;

    public BookAdapter(Context c, ArrayList<Books> b){
        context=c;
        books=b;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(context).inflate(R.layout.book_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.name.setText(books.get(position).getName());
        holder.author1.setText(books.get(position).getAuthor1());
        holder.quantity.setText(books.get(position).getQuantity());
        Picasso.get().load(books.get(position).getimage_link()).into(holder.bookCover);
        holder.bookImage=books.get(position).getimage_link();
        final String clckedImageLink=holder.bookImage;
        final String clickedName=holder.name.getText().toString();
        final String clickedAuthor=holder.author1.getText().toString();
        final String clickedQuantity=holder.quantity.getText().toString();
        final LinearLayout cardView=holder.cardLayout;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cardView.getContext(), BookDetail.class);
                intent.putExtra("name",clickedName);
                intent.putExtra("author",clickedAuthor);
                intent.putExtra("quantity",clickedQuantity);
                intent.putExtra("image_link",clckedImageLink);
                cardView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView name, author1, quantity;
        ImageView bookCover;
        LinearLayout cardLayout;
        String bookImage;
        public BookViewHolder (View itemView){
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.book_title);
            author1=(TextView)itemView.findViewById(R.id.book_author);
            quantity=(TextView)itemView.findViewById(R.id.availability);
            bookCover=(ImageView)itemView.findViewById(R.id.book_image);
            cardLayout = (LinearLayout)itemView.findViewById(R.id.full_card);
            bookImage=null;
        }
    }

}

