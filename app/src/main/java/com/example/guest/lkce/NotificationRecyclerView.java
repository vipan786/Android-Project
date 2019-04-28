package com.example.guest.lkce;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NotificationRecyclerView extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification_recycler_view);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Notifications");
        recyclerView = findViewById(R.id.notificationrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotificationRecyclerView.this));
        final NotificationAdapter myAdapter = new NotificationAdapter(recyclerView, NotificationRecyclerView.this, new ArrayList<String>(), new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String filename = dataSnapshot.getValue(String.class);
                String url = dataSnapshot.getValue(String.class);
                ((NotificationAdapter) recyclerView.getAdapter()).update(filename, url);

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
