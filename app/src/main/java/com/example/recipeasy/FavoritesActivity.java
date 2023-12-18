package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.recipeasy.BackEnd.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView recyclerView;
    FavoritesAdapter favoritesAdapter;
    ArrayList<Recipe> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favorites = Controller.getFavorites().getRecipes();

        recyclerView = findViewById(R.id.favorites_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoritesAdapter = new FavoritesAdapter(this, favorites, this);
        recyclerView.setAdapter(favoritesAdapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.favorites);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.favorites) {
                return true;
            } else if (itemId == R.id.shopping_cart) {
                startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.fridge) {
                startActivity(new Intent(getApplicationContext(), FridgeActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.recipe) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.settings) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(FavoritesActivity.this, SingleRecipeActivity.class);

        intent.putExtra("NAME", Controller.getRecommendation().getRecipes().get(position).getName());
        intent.putExtra("SERVE", Controller.getRecommendation().getRecipes().get(position).getServings());
        intent.putExtra("TIME", Controller.getRecommendation().getRecipes().get(position).getCookingTime());
        intent.putExtra("DIRECTIONS", Controller.getRecommendation().getRecipes().get(position).getDirections());
        intent.putExtra("IMAGE_URL", Controller.getRecommendation().getRecipes().get(position).getImageURL());


        startActivity(intent);
    }
}