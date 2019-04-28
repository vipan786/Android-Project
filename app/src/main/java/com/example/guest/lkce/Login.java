package com.example.guest.lkce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private EditText loginemailtext;
    private EditText loginpasstext;
    private Button loginbutton;
    private ProgressBar processbar;
    private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginemailtext = findViewById(R.id.loginemail);
        loginpasstext = findViewById(R.id.loginpassword);
        loginbutton = findViewById(R.id.loginbutton);
        processbar = findViewById(R.id.progressBar);
        mauth = FirebaseAuth.getInstance();
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginemailtext.getText().toString();
                String password = loginpasstext.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {


                    mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                processbar.setVisibility(View.VISIBLE);
                                sendTOMain();

                            } else {
                                String errr = task.getException().getMessage();
                                Toast.makeText(Login.this, "error " + errr, Toast.LENGTH_LONG).show();


                            }


                        }
                    });

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mauth.getCurrentUser();
        if (currentuser != null) {
            sendTOMain();


        }

    }

    private void sendTOMain() {
        Intent mainintent = new Intent(Login.this, MainActivity.class);
        startActivity(mainintent);
        finish();
    }
}
