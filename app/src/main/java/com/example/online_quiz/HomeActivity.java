package com.example.online_quiz;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        // Connect Back button from XML
        backButton = findViewById(R.id.backButton);

        // Back button
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}
