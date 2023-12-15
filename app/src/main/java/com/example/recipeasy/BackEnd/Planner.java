package com.example.recipeasy.BackEnd;

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
        recipes[day]= recipe;
    }

    public void removeRecipe(int day){
        recipes[day] = null;
    }

    /**
     * Adds recipe to the list. It is a helper method for the controller class. Do not use it! Use addRecipe instead.
     * @param day
     * @param recipe
     */
    public void addRecipeToTheList(int day, Recipe recipe) {
        recipes[day] = recipe;
    }
}
