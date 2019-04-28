package com.example.guest.lkce;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String uid;
    FirebaseUser user;
    String des;
    RecyclerView recyclerView;
    TextView namefield, rollnumberfield, branchfield, book1field, book1_return_datefield, book2field, book2_return_datefield, sessionfield, penaltyfield;
    String name, branch, book1, book1_return_date, book2, book2_return_date, session;
    String rollnumer, penalty, imge;
    ImageView imgefield;
    Context context;

    @Override
    protected void onStart() {


        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);

        } else {


        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("**************234************************************************************************************" + user);

        uid = user.getUid();
        System.out.println("**************234************************************************************************************" + uid);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        namefield = findViewById(R.id.namefield);
        rollnumberfield = findViewById(R.id.rollnumber);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("mynotification", "mynotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }
        branchfield = findViewById(R.id.branch);
        book1field = findViewById(R.id.book1);
        book1_return_datefield = findViewById(R.id.returndate1);
        book2_return_datefield = findViewById(R.id.returndate2);
        book2field = findViewById(R.id.book2);
        sessionfield = findViewById(R.id.session);
        penaltyfield = findViewById(R.id.penalty);
        imgefield = findViewById(R.id.profileimage);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                des = dataSnapshot.child("UID").child(uid).child("Roll Number").getValue(String.class);


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("STUDENTS").child(des).child("Profile");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        name = dataSnapshot.child("Name").getValue().toString();
                        namefield.setText(name);

                        rollnumer = dataSnapshot.child("Roll Number").getValue(String.class);
                        rollnumberfield.setText(rollnumer);
                        session = dataSnapshot.child("Session").getValue().toString();
                        sessionfield.setText(session);
                        branch = dataSnapshot.child("Branch").getValue().toString();
                        branchfield.setText(branch);
                        book1 = dataSnapshot.child("BOOK-1").getValue().toString();
                        book1field.setText(book1);
                        book1_return_date = dataSnapshot.child("BOOK-1 Return Date").getValue().toString();
                        book1_return_datefield.setText(book1_return_date);
                        book2 = dataSnapshot.child("BOOK-2").getValue().toString();
                        book2field.setText(book2);
                        book2_return_date = dataSnapshot.child("BOOK-2 Return Date").getValue().toString();
                        book2_return_datefield.setText(book2_return_date);
                        penalty = dataSnapshot.child("Penalty").getValue(String.class);
                        penaltyfield.setText(penalty);
                        imge = dataSnapshot.child("Image url").getValue(String.class);
                        Picasso.get().load(imge).into(imgefield);
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

        /************************************************/


        /*****************************************************************/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.blog) {

            Intent intent = new Intent(MainActivity.this, BlogRecyclerView.class);
            startActivity(intent);
        } else if (id == R.id.marks) {

            Intent intent = new Intent(MainActivity.this, MarksRecyclerView.class);
            startActivity(intent);


        } else if (id == R.id.notifications) {

            Intent intent = new Intent(MainActivity.this, MessageRecyclerView.class);
            startActivity(intent);

        } else if (id == R.id.downloads) {
            Intent intent = new Intent(MainActivity.this, MyRecyclerView.class);
            startActivity(intent);
        } else if (id == R.id.logout) {

            FirebaseAuth.getInstance().signOut();

            Intent logout = new Intent(MainActivity.this, Login.class);
            startActivity(logout);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
