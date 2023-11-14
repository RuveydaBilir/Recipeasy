package com.example.recipeasy.BackEnd;

import android.media.Image;

import java.util.ArrayList;

public class Recipe {
    String name, directions;
    boolean isLiked, haveAlIngredients, isShown;
    int servings, cookingTime, missingNum;
    Image recipeImage;
    ArrayList<Ingredient> recipeIngredients;

    public Recipe(String name, String directions, int servings, int cookingTime, Image recipeImage, ArrayList<Ingredient> recipeIngredients) {
        this.name = name;
        this.directions = directions;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.recipeImage = recipeImage;
        this.recipeIngredients = recipeIngredients;
        isLiked = false;
    }

}

