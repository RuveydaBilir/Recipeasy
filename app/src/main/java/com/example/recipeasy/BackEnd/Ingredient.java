
package com.example.recipeasy.BackEnd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class Ingredient implements Serializable {
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

    public double updateAmount(double increment){
        double newAmount = amount + increment;

        return newAmount;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Ingredient){
            Ingredient ing = (Ingredient) obj;
            if(this.name.equals(ing.name)){
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return amount + " " + measureType + " " +  name;
    }
}
