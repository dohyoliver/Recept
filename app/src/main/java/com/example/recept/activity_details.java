package com.example.recept;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class activity_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnBack = findViewById(R.id.btnBack);


        String title = getIntent().getStringExtra("title");
        String quality = getIntent().getStringExtra("author");
        int difficulty = getIntent().getIntExtra("pages", 0);
        int randomYear = new Random().nextInt(2023 - 1900) + 1900;


        tvDetails.setText("Recept neve: " + title +
                "\nMinőség: " + quality +
                "\nNehézség: " + difficulty +
                "\nVéletlen évszám: " + randomYear);


        btnBack.setOnClickListener(view -> finish());
    }
}