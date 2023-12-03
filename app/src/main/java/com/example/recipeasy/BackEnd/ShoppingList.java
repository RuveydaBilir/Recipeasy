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
}
