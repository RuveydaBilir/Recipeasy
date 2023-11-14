
package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Ingredient {
    String name, measureType, type;
    double amount;
    boolean doesExist;
    public Ingredient(String name){
        this.name = name;
        amount = 0;
        doesExist = false;
    }


    /**
     * if the ingredient does not exist in the list, it changes the does exist boolean; if it exists, just increases the amount.
     * @param list the list of ingredients of either the fridge or basket
     * @param amount the amount of the ingredient
     */
    public void increaseAmount(ArrayList<Ingredient> list, double amount){

    }

    public String getName() {
        return name;
    }

    public String getMeasureType() {
        return measureType;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isDoesExist() {
        return doesExist;
    }
}
