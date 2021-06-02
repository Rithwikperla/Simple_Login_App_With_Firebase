package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {
    Button mLogout;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mLogout = findViewById(R.id.Logout);



        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(HomeScreen.this,login.class);
                startActivity(intent);
            }
        });
    }
}