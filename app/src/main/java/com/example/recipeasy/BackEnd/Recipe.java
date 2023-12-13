package com.example.recipeasy.BackEnd;

import android.media.Image;

import java.util.ArrayList;

public class Recipe {
    private String name, directions;
    private int servings, cookingTime;
    private Image recipeImage;
    private ArrayList<Ingredient> ingredients;

    public Recipe() {
    }

    public Recipe(String name, String directions,  int servings, int cookingTime, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.directions = directions;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.recipeImage = recipeImage;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Image getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(Image recipeImage) {
        this.recipeImage = recipeImage;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setRecipeIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

