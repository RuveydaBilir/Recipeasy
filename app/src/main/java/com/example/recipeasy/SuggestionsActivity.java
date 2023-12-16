package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SuggestionsActivity extends AppCompatActivity {

    Button sendButton;

    ImageButton backButton;
    EditText suggestionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.settings);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.recipe) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.shopping_cart) {
                startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.fridge) {
                startActivity(new Intent(getApplicationContext(), FridgeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.favorites) {
                startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.settings) {
                return true;
            }
            return false;
        });

        sendButton = findViewById(R.id.suggestions_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();

                if(itemId == R.id.suggestions_button){
                    //TODO: receive user's suggestions?
                }
            }
        });

        backButton = findViewById(R.id.suggestions_return_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();
                if(itemId == R.id.suggestions_return_button){
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    finish();
                }
            }
        });

    }
}