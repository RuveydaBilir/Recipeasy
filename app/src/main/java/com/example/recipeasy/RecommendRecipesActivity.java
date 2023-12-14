package com.example.recipeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;


import com.example.recipeasy.BackEnd.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecommendRecipesActivity extends AppCompatActivity {
    private ImageButton backButton;
    private Button servingsButton;
    private Button timeButton;
    private Button filterButton;
    private Button sortButton;
    private CheckBox servings_checkbox1;
    private CheckBox servings_checkbox2;
    private CheckBox servings_checkbox3;
    private CheckBox servings_checkbox4;
    private CheckBox servings_checkbox5;
    private CheckBox time_checkbox1;
    private CheckBox time_checkbox2;
    private CheckBox time_checkbox3;
    private CheckBox time_checkbox4;
    private CheckBox time_checkbox5;
    private CheckBox sort_checkbox1;
    private CheckBox sort_checkbox2;
    private CheckBox sort_checkbox3;
    private CheckBox sort_checkbox4;

    RecyclerView recyclerView;
    DatabaseReference database;
    RecipeAdapter recipeAdapter;
    ArrayList<Recipe> recipes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_recipes);
        recyclerView = findViewById(R.id.recipes_recyclerView);
        database = FirebaseDatabase.getInstance().getReference("recipes");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipes = new ArrayList<>();
        RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes);
        recyclerView.setAdapter(recipeAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Recipe recipe = dataSnapshot.getValue(Recipe.class);
                    recipes.add(recipe);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.recipe);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.recipe) {
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



        backButton = findViewById(R.id.recipes_return_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();

                if(itemId == R.id.recipes_return_button) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });


        filterButton = findViewById(R.id.btnFilter);
        sortButton = findViewById(R.id.btnSort);
        servingsButton = findViewById(R.id.btnServings);
        timeButton = findViewById(R.id.btnTime);
        sort_checkbox1 = findViewById(R.id.checkbox_sort_time1);
        sort_checkbox2 = findViewById(R.id.checkbox_sort_time2);
        sort_checkbox3 = findViewById(R.id.checkbox_sort_servings1);
        sort_checkbox4 = findViewById(R.id.checkbox_sort_servings2);
        servings_checkbox1 = findViewById(R.id.checkbox_2serv);
        servings_checkbox2 = findViewById(R.id.checkbox_4serv);
        servings_checkbox3 = findViewById(R.id.checkbox_6serv);
        servings_checkbox4 = findViewById(R.id.checkbox_8serv);
        servings_checkbox5 = findViewById(R.id.checkbox_8plus_serv);
        time_checkbox1 = findViewById(R.id.checkbox_filter_time1);
        time_checkbox2 = findViewById(R.id.checkbox_filter_time2);
        time_checkbox3 = findViewById(R.id.checkbox_filter_time3);
        time_checkbox4 = findViewById(R.id.checkbox_filter_time4);
        time_checkbox5 = findViewById(R.id.checkbox_filter_time5);

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                showSortCheckboxes();
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                showFilterOptions();
            }
        });

        servingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                showFilterServingsCheckboxes();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                showFilterTimeCheckboxes();
            }
        });


    }
    private void showSortCheckboxes() {
        // Show checkboxes when servings button is clicked
        sort_checkbox1.setVisibility(View.VISIBLE);
        sort_checkbox2.setVisibility(View.VISIBLE);
        sort_checkbox3.setVisibility(View.VISIBLE);
        sort_checkbox4.setVisibility(View.VISIBLE);
        // Make more checkboxes visible as needed
    }

    private void showFilterOptions() {
        // Show checkboxes when servings button is clicked
        servingsButton.setVisibility(View.VISIBLE);
        timeButton.setVisibility(View.VISIBLE);
        // Make more checkboxes visible as needed
    }

    private void showFilterServingsCheckboxes() {
        // Show checkboxes when servings button is clicked
        servings_checkbox1.setVisibility(View.VISIBLE);
        servings_checkbox2.setVisibility(View.VISIBLE);
        servings_checkbox3.setVisibility(View.VISIBLE);
        servings_checkbox4.setVisibility(View.VISIBLE);
        servings_checkbox5.setVisibility(View.VISIBLE);
        // Make more checkboxes visible as needed
    }

    private void showFilterTimeCheckboxes() {
        // Show checkboxes when servings button is clicked
        time_checkbox1.setVisibility(View.VISIBLE);
        time_checkbox2.setVisibility(View.VISIBLE);
        time_checkbox3.setVisibility(View.VISIBLE);
        time_checkbox4.setVisibility(View.VISIBLE);
        time_checkbox5.setVisibility(View.VISIBLE);
        // Make more checkboxes visible as needed
    }

}