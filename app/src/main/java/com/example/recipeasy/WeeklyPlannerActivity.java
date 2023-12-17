package com.example.recipeasy;

import static java.util.Arrays.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.Planner;
import com.example.recipeasy.BackEnd.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class WeeklyPlannerActivity extends AppCompatActivity {

    ImageButton backButton;

    RecyclerView recyclerView;
    WeeklyPlannerAdapter weeklyPlannerAdapter;
    ArrayList<Recipe> weeklyRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_planner);

        setWeeklyRecipes();
        recyclerView = findViewById(R.id.weekly_planner_recycler_view_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weeklyPlannerAdapter = new WeeklyPlannerAdapter(this, weeklyRecipes);
        recyclerView.setAdapter(weeklyPlannerAdapter);


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

        backButton = findViewById(R.id.weekly_planner_return_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();

                if(itemId == R.id.weekly_planner_return_button){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }
        });
    }

    private void setWeeklyRecipes(){
        //TODO: Test amacli tum recipeleri aldim ama Controller.getPlanner().getRecipes() calismiyor - null pointer exception
        //Recipe[] arr = Controller.getPlanner().getRecipes();
        ArrayList<Recipe> arr = Controller.getAllRecipes();
        weeklyRecipes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if(arr != null){
                weeklyRecipes.add(arr.get(i));
            }
            else{
                Log.d("Weekly Arr: ", "null");
            }
        }
    }
}