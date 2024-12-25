package com.example.recept;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<recipe> recipeList;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText etTitle = findViewById(R.id.rTitle);
        EditText etQuality = findViewById(R.id.rQuality);
        EditText etDifficulty = findViewById(R.id.rDifficulty);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView lvRecipes = findViewById(R.id.lvBooks);


        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(this, recipeList);
        lvRecipes.setAdapter(adapter);


        btnAdd.setOnClickListener(view -> {
            String title = etTitle.getText().toString().trim();
            String qualityStr = etQuality.getText().toString().trim();
            String difficultyStr = etDifficulty.getText().toString().trim();

            if (title.isEmpty() || qualityStr.isEmpty() || difficultyStr.isEmpty()) {
                Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                return;
            }

            int quality, difficulty;
            try {
                quality = Integer.parseInt(qualityStr);
                difficulty = Integer.parseInt(difficultyStr);

                if (quality < 1 || quality > 100) {
                    Toast.makeText(this, "A minőség 1 és 100 között kell legyen!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (difficulty < 0 || difficulty > 3) {
                    Toast.makeText(this, "A nehézség 0 és 3 között kell legyen!", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Csak számokat írj be a mezőkbe!", Toast.LENGTH_SHORT).show();
                return;
            }


            recipe recipes = new recipe(title, quality, difficulty);
            recipeList.add(recipes);
            adapter.notifyDataSetChanged();


            etTitle.setText("");
            etQuality.setText("");
            etDifficulty.setText("");
        });

        lvRecipes.setOnItemClickListener((parent, view, position, id) -> {
            recipe selectedRecipe = recipeList.get(position);
            Intent intent = new Intent(MainActivity.this, activity_details.class);
            intent.putExtra("title", selectedRecipe.getTitle());
            intent.putExtra("quality", selectedRecipe.getQuality());
            intent.putExtra("difficulty", selectedRecipe.getDifficulty());
            startActivity(intent);
        });
    }
}