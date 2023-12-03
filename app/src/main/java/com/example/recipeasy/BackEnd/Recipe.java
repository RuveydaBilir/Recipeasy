package com.example.recipeasy.BackEnd;

import android.media.Image;

import java.util.ArrayList;

public class Recipe {
    private String name, directions;
    private boolean isLiked, haveAlIngredients, isShown;
    private int servings, cookingTime, missingNum;
    private Image recipeImage;
    private ArrayList<Ingredient> recipeIngredients;

    public Recipe() {
    }

    public Recipe(String name, String directions, boolean isLiked, boolean haveAlIngredients, boolean isShown, int servings, int cookingTime, int missingNum, Image recipeImage, ArrayList<Ingredient> recipeIngredients) {
        this.name = name;
        this.directions = directions;
        this.isLiked = isLiked;
        this.haveAlIngredients = haveAlIngredients;
        this.isShown = isShown;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.missingNum = missingNum;
        this.recipeImage = recipeImage;
        this.recipeIngredients = recipeIngredients;
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

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isHaveAlIngredients() {
        return haveAlIngredients;
    }

    public void setHaveAlIngredients(boolean haveAlIngredients) {
        this.haveAlIngredients = haveAlIngredients;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
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

    public int getMissingNum() {
        return missingNum;
    }

    public void setMissingNum(int missingNum) {
        this.missingNum = missingNum;
    }

    public Image getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(Image recipeImage) {
        this.recipeImage = recipeImage;
    }

    public ArrayList<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}

