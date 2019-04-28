package com.example.guest.lkce;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessageRecyclerView extends AppCompatActivity {
    RecyclerView recyclerView;

    String uid;
    FirebaseUser user;

    @Override
    protected void onStart() {


        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(MessageRecyclerView.this, Login.class);
            startActivity(intent);

        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_recyler_view);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Notifications");
        recyclerView = findViewById(R.id.messagerecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MessageRecyclerView.this));
        final MessageAdapter myAdapter = new MessageAdapter(recyclerView, MessageRecyclerView.this, new ArrayList<String>(), new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String filename = dataSnapshot.getValue(String.class);
                String url = dataSnapshot.getValue(String.class);
                ((MessageAdapter) recyclerView.getAdapter()).update(filename, url);
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
