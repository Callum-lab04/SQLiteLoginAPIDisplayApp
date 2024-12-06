package com.example.sqlitelogindatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText txtEmail, txtPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (!email.contains("@")) {
                        Toast.makeText(getApplicationContext(), "Invalid email format. '@' symbol is required.", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean checkEmail = db.checkEmail(email);
                        if (checkEmail) {
                            Boolean checkPassword = db.checkPassword(email, password);
                            if (checkPassword) {
                                Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, IpAPIScreen.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No account found with this email", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterScreen.class);
                startActivity(i);
            }
        });
    }
}
