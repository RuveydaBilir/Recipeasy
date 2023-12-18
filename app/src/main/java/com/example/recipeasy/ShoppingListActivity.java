package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShoppingListActivity extends AppCompatActivity {

    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.shopping_cart);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.recipe) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.shopping_cart) {
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
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                finish();
                return true;
            }
            return false;
        });

        addButton = findViewById(R.id.fridge_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoppingListActivity.this, ListSearchForIngredientActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}