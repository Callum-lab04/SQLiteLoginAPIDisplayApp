package com.example.sqlitelogindatabaseapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterScreen extends AppCompatActivity {
    EditText name, password, confirm;
    Button registerBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        db = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.newTxtEmail);
        password = (EditText) findViewById(R.id.newTxtPassword);
        confirm = (EditText) findViewById(R.id.confirmPassword);

        registerBtn = (Button) findViewById(R.id.newBtnRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_s = name.getText().toString();
                String password_s = password.getText().toString();
                String confirm_s = confirm.getText().toString();

                if (name_s.equals("") || password.equals("") || confirm_s.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (!name_s.contains("@")) {
                        Toast.makeText(getApplicationContext(), "Invalid email format. '@' symbol is required.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password_s.equals(confirm_s)) {
                            Boolean checkEmail = db.checkEmail(name_s);
                            if (checkEmail == false) {
                                Boolean insert = db.insert(name_s, password_s);
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Account registered", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(RegisterScreen.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Not registered", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}


