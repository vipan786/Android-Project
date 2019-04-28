package com.example.guest.lkce;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MarksRecyclerView extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editText;
    String des;


    String roll = "def";
    String uid;
    FirebaseUser user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marks_recycler_view);


        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                des = dataSnapshot.child("UID").child(uid).child("Roll Number").getValue(String.class);
                System.out.println("***************************************************************************************************" + des);


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("STUDENTS").child(des).child("Marks");
                recyclerView = findViewById(R.id.marksrecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(MarksRecyclerView.this));
                final MarksAdapter myAdapter = new MarksAdapter(recyclerView, MarksRecyclerView.this, new ArrayList<String>(), new ArrayList<String>());
                recyclerView.setAdapter(myAdapter);
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String filename = dataSnapshot.getKey();
                        String url = dataSnapshot.getValue(String.class);
                        ((MarksAdapter) recyclerView.getAdapter()).update(filename, url);
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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*************************************************************************************************************/


        /*****************************************************************************************************/


    }
}
