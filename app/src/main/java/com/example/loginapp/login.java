package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginbtn;
    TextView mCreateacc,mForgotPass;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.LoginMail);
        mPassword = findViewById((R.id.LoginPassword));
        mLoginbtn = findViewById(R.id.Login);
        mCreateacc = findViewById(R.id.CreateView);
        mForgotPass = findViewById(R.id.ForgotView);

        fAuth = FirebaseAuth.getInstance();

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required.");
                    return;
                }
                if (password.length() < 8) {
                    mPassword.setError("Password must be 8 characters.");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(fAuth.getCurrentUser().isEmailVerified()){
                                Toast.makeText(login.this, "Logged in Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this, HomeScreen.class));
                                finish();
                            }
                            else{
                                Toast.makeText(login.this, "Verify your email address.", Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(login.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        mCreateacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Registration.class);
                startActivity(intent);
            }
        });
        mForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, ResetPassword.class);
                startActivity(intent);
            }
        });


    }
}