
package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Ingredient {
    private String name, measureType, category;
    private double amount;

    public Ingredient() {
    }

    public Ingredient(String name, String measureType, String category, double amount) {
        this.name = name;
        this.measureType = measureType;
        this.category = category;
        this.amount = amount;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isDoesExist() {
        return amount > 0;
    }

    public int updateAmount(double increment){
        int newAmount = 0;
        newAmount+= increment;
        if(amount < 0){
            newAmount = 0;
        }
        return newAmount;

    }
}
