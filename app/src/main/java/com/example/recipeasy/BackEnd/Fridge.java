package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Fridge {
    ArrayList<Ingredient> fridgeList;

    /**
     * Constructs a fridge Arraylist containing every possible ingredient
     */
    public Fridge(){
        fridgeList = new ArrayList<Ingredient>();
    }

    public ArrayList<Ingredient> getFridgeList() {
        return fridgeList;
    }
}
