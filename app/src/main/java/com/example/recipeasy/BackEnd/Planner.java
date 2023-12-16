package com.example.recipeasy.BackEnd;

import com.example.recipeasy.Controller;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Planner {

    private Recipe[] recipes;

    public Planner() {
        recipes = new Recipe[5];
    }

    public Planner(Recipe[] recipes) {
        this.recipes = recipes;
    }

    public Recipe[] getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe[] recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(int day, Recipe recipe){
        if(day <= 4 && day >= 0) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Planner").child("recipes");
            reference.child("" + day).setValue(recipe);
        }
    }

    public void removeRecipe(int day){
        if(day <= 4 && day >= 0 && recipes[day] != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Planner").child("recipes");
            reference.child("" + day).removeValue();
        }
    }

}
