package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Fridge_SearchForIngredientActivity extends AppCompatActivity {

    ImageButton backButton;

    private androidx.appcompat.widget.SearchView searchView;
    private RecyclerView search_recycler_view;
    private FridgeItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_search_for_ingredient);

        search_recycler_view = findViewById(R.id.search_bar_fridge_ingredient);
        search_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new FridgeItemAdapter(Controller.getAllIngredients());
        search_recycler_view.setAdapter(itemAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.fridge);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.recipe) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.shopping_cart) {
                startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.fridge) {
                return true;
            } else if (itemId == R.id.favorites) {
                startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
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

        backButton = findViewById(R.id.fridge_search_return_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FridgeActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        searchView = findViewById(R.id.fridge_search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        ArrayList<Ingredient> filteredList = new ArrayList<Ingredient>();
        for (Ingredient r : Controller.getAllIngredients()) {
            if (containsContiguousSubstring(r.getName().toLowerCase(), text.toLowerCase())) {
                filteredList.add(r);
            }
        }
        Log.d("uyarı", text);

        if (filteredList.isEmpty()) {
            Toast.makeText(this, filteredList.size() + "size is", Toast.LENGTH_SHORT).show();
            search_recycler_view.setVisibility(View.GONE);
        } else {
            search_recycler_view.setVisibility(View.VISIBLE);
            itemAdapter.setFilteredList(filteredList);
            search_recycler_view.setAdapter(itemAdapter);
        }
    }

    private static boolean containsContiguousSubstring(String str, String substring) {
        int strLength = str.length();
        int subLength = substring.length();

        for (int i = 0; i <= strLength - subLength; i++) {
            // Check if the substring starting from index i is equal to the desired substring
            if (str.substring(i, i + subLength).equals(substring)) {
                return true;
            }
        }

        // If no match is found, return false
        return false;
    }
}
