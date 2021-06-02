package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Registration extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mRegisterbtn;
    TextView mLoginbtn;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById((R.id.Password));
        mRegisterbtn = findViewById(R.id.signup);
        mLoginbtn = findViewById(R.id.ClickLogin);

        fAuth = FirebaseAuth.getInstance();

        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email  = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }
                if(password.length()<8){
                    mPassword.setError("Password must be 8 characters.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Registration.this,"Successful.Please check your mail.",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Registration.this, login.class));
                                    finish();
                                }
                            });

                        }else {
                            Toast.makeText(Registration.this,"Error!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,login.class);
                startActivity(intent);

            }
        });

    }
}