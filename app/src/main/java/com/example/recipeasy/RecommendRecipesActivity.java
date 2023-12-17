package com.example.recipeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;


import com.example.recipeasy.BackEnd.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecommendRecipesActivity extends AppCompatActivity implements RecyclerViewInterface{
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
    private RadioGroup radioGroup;
    private RadioButton selectedRadioButton;
    private RadioButton sort_time_i;
    private RadioButton sort_time_d;
    private RadioButton sort_servings_i;
    private RadioButton sort_servings_d;
    ConstraintLayout filterOpt;
    ConstraintLayout servingsOpt;
    ConstraintLayout timeOpt;
    ConstraintLayout sortOpt;
    ArrayList<Integer> servings = new ArrayList<>();
    ArrayList<Integer> timeList = new ArrayList<>();
    /*RelativeLayout filterOpt;
    RelativeLayout servingsOpt;
    RelativeLayout timeOpt;
    RelativeLayout sortOpt;*/
    private boolean filterButtonsVisible = false;
    private boolean servingsCheckboxesVisible = false;
    private boolean timeCheckboxesVisible = false;
    private boolean sortCheckboxesVisible = false;

    RecyclerView recyclerView;
    //DatabaseReference database;
    RecipeAdapter recipeAdapter;
    ArrayList<Recipe> recipes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_recipes);
        recipes = Controller.getRecommendation().getRecipes();
        Controller.getRecommendation().mainSort();

        recyclerView = findViewById(R.id.recipes_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(this, recipes, this);
        recyclerView.setAdapter(recipeAdapter);


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
        filterOpt = findViewById(R.id.layoutFilterOptions);
        servingsOpt = findViewById(R.id.layoutServingsOptions);
        timeOpt = findViewById(R.id.layoutTimeOptions);
        sortOpt = findViewById(R.id.layoutSortOptions);
        sort_time_i = findViewById(R.id.radio_button_sort_time_increasing);
        sort_time_d = findViewById(R.id.radio_button_sort_time_decreasing);
        sort_servings_i = findViewById(R.id.radio_button_sort_servings_increasing);
        sort_servings_d = findViewById(R.id.radio_button_sort_servings_decreasing);
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
        radioGroup = findViewById(R.id.radio_group_sort);


        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                toggleSortVisibility();

                if(filterButtonsVisible == true) {
                    toggleFilterOptVisibility();
                }
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                toggleFilterOptVisibility();

                if(sortCheckboxesVisible == true) {
                   toggleSortVisibility();
                }
            }
        });

        servingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                toggleServingsVisibility();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle single serving button click
                toggleTimeVisibility();
            }
        });
        servings_checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(servings_checkbox1.isChecked()){
                    if(!servings.contains(2)){
                        servings.add(2);
                    }
                }
                else{
                    if(servings.contains(2)){
                        servings.remove(Integer.valueOf(2));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();
            }
        });
        servings_checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ekliyo mu", servings.size()+" " );
                if(servings_checkbox2.isChecked()){
                    if(!servings.contains(4)){
                        servings.add(4);
                    }
                }
                else{
                    if(servings.contains(4)){
                        servings.remove(Integer.valueOf(4));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();
            }
        });
        servings_checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(servings_checkbox3.isChecked()){
                    if(!servings.contains(6)){
                        servings.add(6);
                    }
                }
                else{
                    if(servings.contains(6)){
                        servings.remove(Integer.valueOf(6));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();

            }
        });
        servings_checkbox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(servings_checkbox4.isChecked()){
                    if(!servings.contains(8)){
                        servings.add(8);
                    }
                }
                else{
                    if(servings.contains(8)){
                        servings.remove(Integer.valueOf(8));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();

            }
        });
        time_checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_checkbox1.isChecked()){
                    if(!timeList.contains(15)){
                        timeList.add(15);
                    }
                }
                else{
                    if(timeList.contains(15)){
                        timeList.remove(Integer.valueOf(15));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();

            }
        });
        time_checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_checkbox2.isChecked()){
                    if(!timeList.contains(30)){
                        timeList.add(30);
                    }
                }
                else{
                    if(timeList.contains(30)){
                        timeList.remove(Integer.valueOf(30));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();

            }
        });
        time_checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_checkbox3.isChecked()){
                    if(!timeList.contains(45)){
                        timeList.add(45);
                    }
                }
                else{
                    if(timeList.contains(45)){
                        timeList.remove(Integer.valueOf(45));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();
            }
        });
        time_checkbox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_checkbox4.isChecked()){
                    if(!timeList.contains(60)){
                        timeList.add(60);
                    }
                }
                else{
                    if(timeList.contains(60)){
                        timeList.remove(Integer.valueOf(60));
                    }
                }
                Controller.getRecommendation().filter(timeList, servings);
                recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
                recipeAdapter.notifyDataSetChanged();

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sortOptions(findViewById(checkedId));
            }
        });



        /*servings_checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(servings_checkbox1.isChecked()){
                    servings.add(2);
                }
                else{
                    servings.remove(2);
                }
            }
        }); */
   /* recyclerView.setHasFixedSize( Controller.getRecommendation().filterServings(servings);
        recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
        );*/




    }

    private void sortOptions(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        Controller.getRecommendation().mainSort();
        if (radioId == R.id.radio_button_sort_time_increasing) {
            Controller.getRecommendation().sortTime(true);
        } else if (radioId == R.id.radio_button_sort_time_decreasing) {
            Controller.getRecommendation().sortTime(false);
        } else if (radioId == R.id.radio_button_sort_servings_increasing) {
            Controller.getRecommendation().sortServings(true);
        } else if (radioId == R.id.radio_button_sort_servings_decreasing) {
            Controller.getRecommendation().sortServings(false);
        }
        recipeAdapter.setFilteredList(Controller.getRecommendation().getRecipes());
        recipeAdapter.notifyDataSetChanged();
    }









    private void updateSortCheckboxes() {
        // Show checkboxes when servings button is clicked
        /*sort_checkbox1.setVisibility(View.VISIBLE);
        sort_checkbox2.setVisibility(View.VISIBLE);
        sort_checkbox3.setVisibility(View.VISIBLE);
        sort_checkbox4.setVisibility(View.VISIBLE);*/
        // Make more checkboxes visible as needed
        sortOpt.setVisibility(sortCheckboxesVisible ? View.VISIBLE : View.INVISIBLE);
    }

    private void updateFilterOptions() {
        // Show checkboxes when servings button is clicked
        /*servingsButton.setVisibility(View.VISIBLE);
        timeButton.setVisibility(View.VISIBLE);*/
        // Make more checkboxes visible as needed
        filterOpt.setVisibility(filterButtonsVisible ? View.VISIBLE : View.INVISIBLE);

        if(filterButtonsVisible == false) {
            servingsCheckboxesVisible = false;
            timeCheckboxesVisible = false;
        }

        updateFilterServingsCheckboxes();
        updateFilterTimeCheckboxes();
    }

    private void updateFilterServingsCheckboxes() {
        // Show checkboxes when servings button is clicked
        /*servings_checkbox1.setVisibility(View.VISIBLE);
        servings_checkbox2.setVisibility(View.VISIBLE);
        servings_checkbox3.setVisibility(View.VISIBLE);
        servings_checkbox4.setVisibility(View.VISIBLE);
        servings_checkbox5.setVisibility(View.VISIBLE);*/
        // Make more checkboxes visible as needed
        servingsOpt.setVisibility(servingsCheckboxesVisible ? View.VISIBLE : View.INVISIBLE);
    }

    private void updateFilterTimeCheckboxes() {
        // Show checkboxes when servings button is clicked
        /*time_checkbox1.setVisibility(View.VISIBLE);
        time_checkbox2.setVisibility(View.VISIBLE);
        time_checkbox3.setVisibility(View.VISIBLE);
        time_checkbox4.setVisibility(View.VISIBLE);
        time_checkbox5.setVisibility(View.VISIBLE);*/
        // Make more checkboxes visible as needed
        timeOpt.setVisibility(timeCheckboxesVisible ? View.VISIBLE : View.INVISIBLE);
    }

    private void toggleFilterOptVisibility(){
        filterButtonsVisible = ! filterButtonsVisible;
        updateFilterOptions();
    }

    private void toggleServingsVisibility(){
        servingsCheckboxesVisible = ! servingsCheckboxesVisible;
        updateFilterServingsCheckboxes();
    }

    private void toggleTimeVisibility(){
        timeCheckboxesVisible = ! timeCheckboxesVisible;
        updateFilterTimeCheckboxes();
    }

    private void toggleSortVisibility(){
        sortCheckboxesVisible = ! sortCheckboxesVisible;
        updateSortCheckboxes();
    }


    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(RecommendRecipesActivity.this, SingleRecipeActivity.class);

        intent.putExtra("NAME", Controller.getRecommendation().getRecipes().get(position).getName());
        intent.putExtra("SERVE", Controller.getRecommendation().getRecipes().get(position).getServings());
        intent.putExtra("TIME", Controller.getRecommendation().getRecipes().get(position).getCookingTime());
        intent.putExtra("DIRECTIONS", Controller.getRecommendation().getRecipes().get(position).getDirections());
        intent.putExtra("IMAGE_URL", Controller.getRecommendation().getRecipes().get(position).getImageURL());


        startActivity(intent);
    }
}