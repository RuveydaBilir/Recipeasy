package com.example.recipeasy.BackEnd;

import android.media.Image;
import android.util.Log;

import com.example.recipeasy.Controller;

import java.util.ArrayList;

public class Recipe {
    private String name, directions;
    private int servings, cookingTime;
    private String imageURL;
    private ArrayList<Ingredient> ingredients;

    public Recipe() {
    }


    public Recipe(String name, String directions,  int servings, int cookingTime, String imageURL, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.directions = directions;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setRecipeIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean inFavorites() {
        if(Controller.getFavorites().getRecipes().contains(this)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Recipe recipe = (Recipe)obj;

        if(!recipe.getName().equals(this.getName())) {
            return false;
        }

        return true;
    }
}


