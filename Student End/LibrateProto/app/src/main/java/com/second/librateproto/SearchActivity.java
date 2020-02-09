package com.second.librateproto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    DatabaseReference rootReference= FirebaseDatabase.getInstance().getReference();
    DatabaseReference bookReference=rootReference.child("books");
    SearchView searchView;
    RecyclerView recyclerView;
    BookAdapter adapter;
    ArrayList<Books> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView =(SearchView) findViewById(R.id.search_view);
        recyclerView=(RecyclerView) findViewById(R.id.search_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void firebaseSearch(String text){
        ArrayList<Books> list2=new ArrayList<>();
        for(Books b:list1){
            String name=b.getName().toLowerCase();
            String author=b.getAuthor1().toLowerCase();
            if(name.contains(text)||author.contains(text)){
                list2.add(b);
            }
            adapter=new BookAdapter(SearchActivity.this,list2);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(bookReference!=null){
            bookReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list1=new ArrayList<>();
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            Books b=dataSnapshot1.getValue(Books.class);
                            list1.add(b);
                        }
                        adapter=new BookAdapter(SearchActivity.this,list1);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(SearchActivity.this,"Oh snap...Something went wrong",Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView !=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    firebaseSearch(newText.toLowerCase());
                    return true;
                }
            });
        }
    }
}
