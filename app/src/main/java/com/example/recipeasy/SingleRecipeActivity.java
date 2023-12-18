package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class SingleRecipeActivity extends AppCompatActivity {

    private ImageButton backButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        String name = getIntent().getStringExtra("NAME");
        //String serveNum = getIntent().getStringExtra("SERVE_NUM");
        int serve = getIntent().getIntExtra("SERVE",0);
        int time = getIntent().getIntExtra("TIME",0);
        //String time = getIntent().getStringExtra("TIME");
        String directions = getIntent().getStringExtra("DIRECTIONS");
        String imageURL = getIntent().getStringExtra("IMAGE_URL");
        //int image = getIntent().getIntExtra("IMAGE",0);

        TextView nameTextView = findViewById(R.id.textViewRecipeName);
        TextView serveTextView = findViewById(R.id.textViewServingSizeNum);
        TextView timeTextView = findViewById(R.id.textViewCookingTimeNum);
        TextView directionsTextView = findViewById(R.id.textViewInstructionsVariable);
        ImageView imageView = findViewById(R.id.recipeImage);

        nameTextView.setText(name);
        serveTextView.setText(Integer.toString(serve));
        timeTextView.setText(Integer.toString(time));
        directionsTextView.setText(directions);


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
                /*int itemId = v.getId();

                if(itemId == R.id.single_recipe_return_button) {
                    startActivity(new Intent(getApplicationContext(), RecommendRecipesActivity.class));
                    finish();
                }*/

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



    }
     public Activity getActivity(){
        return null;
     }
}