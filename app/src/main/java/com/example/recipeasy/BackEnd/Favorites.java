package com.example.recipeasy.BackEnd;

import android.util.Log;

import com.example.recipeasy.Controller;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Favorites {

    private ArrayList<Recipe> recipes;

    public Favorites() {
        recipes = new ArrayList<>();
    }

    public Favorites(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Favorites").child("recipes");
        boolean doesContain = false;
        for (int i = 0; i < recipes.size(); i++) {
            if(recipe.getName().equals(recipes.get(i).getName())){
                doesContain = true;
                break;
            }
        }
        if(!doesContain){
            reference.child("" + recipes.size()).setValue(recipe);
        }
    }

    public void removeRecipe(Recipe recipe){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Favorites").child("recipes");
        boolean doesContain = false;
        for (int i = 0; i < recipes.size(); i++) {
            if(recipe.getName().equals(recipes.get(i).getName())){
                reference.child(Integer.toString(i)).removeValue();
            }
        }
    }

}
