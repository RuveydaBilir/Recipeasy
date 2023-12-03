package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Fridge {
    private ArrayList<Ingredient> fridgeList;

    /**
     * Constructs a fridge Arraylist containing every possible ingredient
     */
    public Fridge() {
    }

    public Fridge(ArrayList<Ingredient> fridgeList) {
        this.fridgeList = fridgeList;
    }

    public ArrayList<Ingredient> getFridgeList() {
        return fridgeList;
    }

    public void setFridgeList(ArrayList<Ingredient> fridgeList) {
        this.fridgeList = fridgeList;
    }
}
