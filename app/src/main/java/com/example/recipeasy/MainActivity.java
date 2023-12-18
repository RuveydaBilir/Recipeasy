package com.example.recipeasy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    private ActivityMainBinding binding;
    private SearchView searchView;
    private Button recomRecButton;
    private RecyclerView search_recycler_view;
    private Button weeklyPlannerButton;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_recycler_view = findViewById(R.id.search_bar_recipe_recycler_view);
        search_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(Controller.getAllRecipes(),this);
        search_recycler_view.setAdapter(itemAdapter);
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


        searchView = findViewById(R.id.main_search);
        searchView.clearFocus();
        search_recycler_view.setVisibility(View.GONE);
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

        recomRecButton = findViewById(R.id.recommend_recipes_button);
        recomRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();

                if (itemId == R.id.recommend_recipes_button) {
                    startActivity(new Intent(getApplicationContext(), RecommendRecipesActivity.class));
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
        weeklyPlannerButton = findViewById(R.id.weekly_planner_button);
        weeklyPlannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();

                if(itemId == R.id.weekly_planner_button){
                    startActivity(new Intent(getApplicationContext(), WeeklyPlannerActivity.class));
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
    }
    private ArrayList<Recipe> filteredList;

    private void filterList(String text) {
        filteredList= new ArrayList<Recipe>();
        for (Recipe r: Controller.getAllRecipes()) {
            if (containsContiguousSubstring(r.getName().toLowerCase(), text.toLowerCase()) ) {
                filteredList.add(r);
            }
        }

        if(filteredList.isEmpty() || text == null || text.equals("")){
            //Toast.makeText(this, filteredList.size()+"size is", Toast.LENGTH_SHORT).show();
            search_recycler_view.setVisibility(View.GONE);
        }
        else{
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, SingleRecipeActivity.class);

        intent.putExtra("callerActivity", "MainActivity");
        intent.putExtra("NAME", filteredList.get(position).getName());
        intent.putExtra("SERVE", filteredList.get(position).getServings());
        intent.putExtra("TIME", filteredList.get(position).getCookingTime());
        intent.putExtra("DIRECTIONS", filteredList.get(position).getDirections());
        intent.putExtra("IMAGE_URL", filteredList.get(position).getImageURL());


        startActivity(intent);
    }
}