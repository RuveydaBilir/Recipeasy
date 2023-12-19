package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingleRecipeActivity extends AppCompatActivity {

    private ImageButton backButton;
    private ImageButton favoriteButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        String name = getIntent().getStringExtra("NAME");
        int serve = getIntent().getIntExtra("SERVE",0);
        int time = getIntent().getIntExtra("TIME",0);
        String directions = getIntent().getStringExtra("DIRECTIONS");
        String imageURL = getIntent().getStringExtra("IMAGE_URL");
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) getIntent().getSerializableExtra("INGREDIENTS");
        ArrayList<Ingredient> missingIng = (ArrayList<Ingredient>) getIntent().getSerializableExtra("MISSING");


        TextView nameTextView = findViewById(R.id.textViewRecipeName);
        TextView serveTextView = findViewById(R.id.textViewServingSizeNum);
        TextView timeTextView = findViewById(R.id.textViewCookingTimeNum);
        TextView directionsTextView = findViewById(R.id.textViewInstructionsVariable);
        ImageView imageView = findViewById(R.id.recipeImage);
        LinearLayout linearLayout = findViewById(R.id.layoutIngredients);

        nameTextView.setText(name);
        serveTextView.setText(Integer.toString(serve));
        timeTextView.setText(Integer.toString(time));
        directionsTextView.setText(directions);

        for(Ingredient ing : ingredients){
            TextView textView = new TextView(this);
            textView.setText(ing.toString());
            if(missingIng == null || !missingIng.contains(ing)){
                textView.setTextColor(ContextCompat.getColor(this,R.color.yesil_malzeme));
            }
            else{
                textView.setTextColor(ContextCompat.getColor(this,R.color.kirmizi_malzeme));
            }
            linearLayout.addView(textView);
        }


        //String imageUrl = recipes.get(position).getImageURL();

        Picasso.get().load(imageURL).into(imageView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.recipe);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.recipe) {
                startActivity(new Intent(getApplicationContext(), RecommendRecipesActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
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

        backButton = findViewById(R.id.single_recipe_return_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callerActivity = getIntent().getStringExtra("callerActivity");

                if (callerActivity != null) {
                    switch (callerActivity) {
                        case "RecommendRecipes":
                            startActivity(new Intent(getApplicationContext(), RecommendRecipesActivity.class));
                            finish();
                            break;
                        case "WeeklyPlanner":
                            startActivity(new Intent(getApplicationContext(), WeeklyPlannerActivity.class));
                            finish();
                            break;
                        case "Favorites":
                            startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                            finish();
                            break;
                        default:
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                    }
                }
            }
        });

        favoriteButton = findViewById(R.id.recipes_favorites_button);

        int recipePosition = findRecipePosition(getIntent().getStringExtra("NAME"));
        Recipe thisRecipe;

        thisRecipe = Controller.getAllRecipes().get(recipePosition);

        int heartDrawable = thisRecipe.inFavorites() ? R.drawable.favorite_icon_kirmizi : R.drawable.favorite_icibos;
        favoriteButton.setImageResource(heartDrawable);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newHeartDrawable;
                if(thisRecipe.inFavorites()){
                    Controller.getFavorites().removeRecipe(thisRecipe);
                    newHeartDrawable = R.drawable.favorite_icibos;
                }
                else{
                    Controller.getFavorites().addRecipe(thisRecipe);
                    newHeartDrawable = R.drawable.favorite_icon_kirmizi;
                }
                favoriteButton.setImageResource(newHeartDrawable);
            }
        });



    }

    private int findRecipePosition(String recipeName){
        ArrayList<Recipe> allRecipes = Controller.getAllRecipes();
        recipeName = recipeName.toLowerCase();
        for (int i = 0; i < allRecipes.size(); i++) {
            if(allRecipes.get(i).getName().equalsIgnoreCase(recipeName)){
                return i;
            }
        }

        return allRecipes.size();
    }
}