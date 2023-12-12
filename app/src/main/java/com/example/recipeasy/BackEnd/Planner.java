package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Planner {

    private ArrayList<Recipe> recipes;

    public Planner() {
    }

    public Planner(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(int day, Recipe recipe){
        recipes.add(day, recipe);
    }
    
    public void removeRecipe(int day){
        recipes.remove(day);
    }
}
