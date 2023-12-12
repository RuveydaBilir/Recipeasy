package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> shoppingList;

    /**
     * constructs a basket arraylist containing every possible ingredient
     */
    public ShoppingList() {
    }

    public ShoppingList(ArrayList<Ingredient> basketList) {
        this.shoppingList = basketList;
    }

    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<Ingredient> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void addIngredient(Ingredient ingredient){
        boolean doesContain = false;
        for (int i = 0; i < shoppingList.size(); i++) {
            if(ingredient.getName().equals(shoppingList.get(i).getName())){
                doesContain = true;
                shoppingList.get(i).updateAmount(ingredient.getAmount());
                break;
            }
        }
        if(!doesContain){
            shoppingList.add(ingredient);
        }
    }

    public void removeIngredient(Ingredient ingredient){
        boolean doesContain = false;
        for (int i = 0; i < shoppingList.size(); i++) {
            if(ingredient.getName().equals(shoppingList.get(i).getName())){
                doesContain = true;
                shoppingList.get(i).updateAmount(-ingredient.getAmount());
                break;
            }
        }
    }
}
