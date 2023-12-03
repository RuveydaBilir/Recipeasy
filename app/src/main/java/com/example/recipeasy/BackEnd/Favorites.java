package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Favorites {

    private ArrayList<Recipe> recipes;

    public Favorites() {
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
}
