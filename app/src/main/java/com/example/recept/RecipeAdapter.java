package com.example.recept;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<recipe> {

    public RecipeAdapter(Context context, ArrayList<recipe> recipes) {
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_item, parent, false);
        }

        recipe recipe = getItem(position);
        TextView title = convertView.findViewById(R.id.rTitle);
        TextView quality = convertView.findViewById(R.id.rQuality);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        title.setText(recipe.getTitle());
        quality.setText(recipe.getDifficulty());

        btnDelete.setOnClickListener(view -> new AlertDialog.Builder(getContext())
                .setTitle("Törlés")
                .setMessage("Biztosan törölni szeretnéd?")
                .setPositiveButton("Igen", (dialog, which) -> {
                    remove(recipe);
                    notifyDataSetChanged();
                })
                .setNegativeButton("Nem", null)
                .show());

        return convertView;
    }
}
