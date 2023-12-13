
package com.example.recipeasy.BackEnd;

import java.util.ArrayList;

public class Ingredient {
    private String name, measurementType, category;
    private double amount;

    public Ingredient() {
    }

    public Ingredient(String name, String measurementType, String category, double amount) {
        this.name = name;
        this.measurementType = measurementType;
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
        return measurementType;
    }

    public void setMeasureType(String measurementType) {
        this.measurementType = measurementType;
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

    public void updateAmount(double increment){
        amount += increment;
        if(amount < 0){
            amount = 0;
        }
    }
}
