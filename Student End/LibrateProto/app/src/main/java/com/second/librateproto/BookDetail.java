package com.second.librateproto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookDetail extends AppCompatActivity {

    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
    ArrayList<Books> list;
    String clickedName,clickedAuthor,clickedQuantity,clickedImageLink;
    TextView name,author,quantity;
    ImageView cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);



        Bundle bundle = getIntent().getExtras();

        clickedName=bundle.getString("name");
        clickedAuthor=bundle.getString("author");
        clickedQuantity=bundle.getString("quantity");
        clickedImageLink=bundle.getString("image_link");


        name=(TextView)findViewById(R.id.issued_book_name);
        author=(TextView)findViewById(R.id.issued_book_author);
        quantity=(TextView)findViewById(R.id.issued_book_quantity);
        cover=(ImageView)findViewById(R.id.issued_book_image);
        name.setText(clickedName);
        author.setText(clickedAuthor);
        quantity.setText(clickedQuantity);
        Picasso.get().load(clickedImageLink).into(cover);
    }
    public void onIssueButtonClick(View view){
        final DatabaseReference bookReference= reference.child("books");
        bookReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Books b=dataSnapshot1.getValue(Books.class);
                    String x=b.getName();
                    String q=b.getQuantity();
                    int i=(int) Integer.parseInt(q);
                    if(x.equals(clickedName)&&i>0) {
                        i--;
                        String k = dataSnapshot1.getKey();
                        String al = (String) Integer.toString(i);
                        bookReference.child(k).child("quantity").setValue(al);
                        quantity.setText(al);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BookDetail.this,"Oh snap...Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(BookDetail.this,"Book has been requested to be issued",Toast.LENGTH_LONG).show();
    }
}