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
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    TextView  Backbtn;
    EditText Email;
    FirebaseAuth fAuth;
    Button ResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        fAuth = FirebaseAuth.getInstance();

        ResetPassword = findViewById(R.id.ForgotPass);
        Email = findViewById(R.id.InputMail);
        Backbtn = findViewById(R.id.Backbtn);



        ResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required.");
                    return;
                }

                fAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ResetPassword.this, login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ResetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, login.class);
                startActivity(intent);
            }
        });
    }
}