package com.second.librateproto.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.second.librateproto.BookAdapter;
import com.second.librateproto.Books;
import com.second.librateproto.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference bookReference= reference.child("books");
        bookReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                RecyclerView recyclerView=(RecyclerView)root.findViewById(R.id.book_recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                ArrayList<Books> list=new ArrayList<Books>();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Books b=dataSnapshot1.getValue(Books.class);
                    list.add(b);
                }
                BookAdapter adapter=new BookAdapter(getActivity(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Oh snap...Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}