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

    public void addRecipe(Recipe recipe){
        boolean doesContain = false;
        for (int i = 0; i < recipes.size(); i++) {
            if(recipe.getName().equals(recipes.get(i).getName())){
                doesContain = true;
                break;
            }
        }
        if(!doesContain){
            recipes.add(recipe);
        }
    }

    public void removeRecipe(Ingredient recipe){
        boolean doesContain = false;
        for (int i = 0; i < recipes.size(); i++) {
            if(recipe.getName().equals(recipes.get(i).getName())){
                doesContain = true;
                recipes.remove(recipes.get(i));
            }
        }
    }
}
