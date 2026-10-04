package com.example.online_quiz;

import android.content.Intent;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    EditText name, phone, email, password;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java with XML
        setContentView(R.layout.activity_create_account);

        // Connect XML elements
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        createButton = findViewById(R.id.createButton);

        // Create Account button
        createButton.setOnClickListener(v -> {

            String userName = name.getText().toString().trim();
            String userPhone = phone.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            // Check whether all fields are filled
            if (userName.isEmpty() ||
                    userPhone.isEmpty() ||
                    userEmail.isEmpty() ||
                    userPassword.isEmpty()) {

                Toast.makeText(
                        CreateAccountActivity.this,
                        "Please fill all details",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                // Save user details
                SharedPreferences preferences =
                        getSharedPreferences("UserData", MODE_PRIVATE);

                SharedPreferences.Editor editor =
                        preferences.edit();

                editor.putString("name", userName);
                editor.putString("phone", userPhone);
                editor.putString("email", userEmail);
                editor.putString("password", userPassword);

                editor.apply();

                // Show success message
                Toast.makeText(
                        CreateAccountActivity.this,
                        "Account created successfully",
                        Toast.LENGTH_SHORT
                ).show();

                // Go back to Login page
                Intent intent = new Intent(
                        CreateAccountActivity.this,
                        MainActivity.class
                );

                startActivity(intent);

                finish();
            }
        });
    }
}