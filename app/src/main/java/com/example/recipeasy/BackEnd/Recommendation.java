package com.example.recipeasy.BackEnd;
import java.util.ArrayList;


public class Recommendation {
    private ArrayList<Recipe> recipes;

    public Recommendation() {
    }

    public Recommendation(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void filterServings(int servings){

    }
    public void filterTime(){

    }
    public void sortServings(){

    }
    public void sortTime(){

    }
    public void mainSort(){

    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
}
