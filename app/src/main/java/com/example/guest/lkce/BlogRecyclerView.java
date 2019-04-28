package com.example.guest.lkce;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BlogRecyclerView extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView img;
    public int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog_recycler_view);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");
        recyclerView = findViewById(R.id.blogrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(BlogRecyclerView.this));
        final BlogAdapter myAdapter = new BlogAdapter(recyclerView, BlogRecyclerView.this, new ArrayList<String>(), new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                String url = dataSnapshot.getValue(String.class);
                String desciption = dataSnapshot.getKey();


                ((BlogAdapter) recyclerView.getAdapter()).update(desciption, url);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

