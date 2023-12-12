
package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Ingredient {
    private String name, measureType, type;
    private double amount;
    private boolean doesExist;

    public Ingredient() {
    }

    public Ingredient(String name, String measureType, String type, double amount, boolean doesExist) {
        this.name = name;
        this.measureType = measureType;
        this.type = type;
        this.amount = amount;
        this.doesExist = doesExist;
    }

    /**
     * if the ingredient does not exist in the list, it changes the does exist boolean; if it exists, just increases the amount.
     * @param list the list of ingredients of either the fridge or basket
     * @param amount the amount of the ingredient
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isDoesExist() {
        return doesExist;
    }

    public void setDoesExist(boolean doesExist) {
        this.doesExist = doesExist;
    }
    public void updateAmount(double increment){
        amount += increment;
        if(amount < 0){
            amount = 0;
        }
    }
}
