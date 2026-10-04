package com.example.online_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginButton;
    TextView createAccount, forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Open Login page
        setContentView(R.layout.activity_main);

        // Connect XML elements with Java
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        createAccount = findViewById(R.id.createAccount);
        forgotPassword = findViewById(R.id.forgotPassword);


        // =========================================
        // CREATE ACCOUNT
        // =========================================

        createAccount.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    CreateAccountActivity.class
            );

            startActivity(intent);
        });


        // =========================================
        // FORGOT PASSWORD
        // =========================================

        forgotPassword.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ForgotPasswordActivity.class
            );

            startActivity(intent);
        });


        // =========================================
        // LOGIN
        // =========================================

        loginButton.setOnClickListener(v -> {

            // Get email entered by user
            String enteredEmail =
                    email.getText().toString().trim();

            // Get password entered by user
            String enteredPassword =
                    password.getText().toString().trim();


            // Check empty fields
            if (enteredEmail.isEmpty() ||
                    enteredPassword.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // Get saved account details
            SharedPreferences preferences =
                    getSharedPreferences(
                            "UserData",
                            MODE_PRIVATE
                    );

            String savedEmail =
                    preferences.getString("email", "");

            String savedPassword =
                    preferences.getString("password", "");


            // Check email and password
            if (enteredEmail.equals(savedEmail) &&
                    enteredPassword.equals(savedPassword)) {

                // Login successful
                Toast.makeText(
                        MainActivity.this,
                        "Login successful",
                        Toast.LENGTH_SHORT
                ).show();


                // Open Home page
                Intent intent = new Intent(
                        MainActivity.this,
                        HomeActivity.class
                );

                startActivity(intent);

            } else {

                // Wrong email or password
                Toast.makeText(
                        MainActivity.this,
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}