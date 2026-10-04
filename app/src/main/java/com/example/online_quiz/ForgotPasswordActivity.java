package com.example.online_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText resetEmail, newPassword;
    Button resetButton;
    TextView backToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Open Forgot Password page
        setContentView(R.layout.activity_forgot_password);

        // Connect XML with Java
        resetEmail = findViewById(R.id.resetEmail);
        newPassword = findViewById(R.id.newPassword);
        resetButton = findViewById(R.id.resetButton);
        backToLogin = findViewById(R.id.backToLogin);

        // Change Password button
        resetButton.setOnClickListener(v -> {

            String enteredEmail =
                    resetEmail.getText().toString().trim();

            String enteredNewPassword =
                    newPassword.getText().toString().trim();

            if (enteredEmail.isEmpty() ||
                    enteredNewPassword.isEmpty()) {

                Toast.makeText(
                        ForgotPasswordActivity.this,
                        "Please enter email and new password",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Get saved account
            SharedPreferences preferences =
                    getSharedPreferences("UserData", MODE_PRIVATE);

            String savedEmail =
                    preferences.getString("email", "");

            // Check email
            if (enteredEmail.equals(savedEmail)) {

                // Save new password
                SharedPreferences.Editor editor =
                        preferences.edit();

                editor.putString(
                        "password",
                        enteredNewPassword
                );

                editor.apply();

                Toast.makeText(
                        ForgotPasswordActivity.this,
                        "Password changed successfully",
                        Toast.LENGTH_LONG
                ).show();

                // Go to Login
                Intent intent = new Intent(
                        ForgotPasswordActivity.this,
                        MainActivity.class
                );

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        ForgotPasswordActivity.this,
                        "Email not found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Back to Login
        backToLogin.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ForgotPasswordActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();
        });
    }
}