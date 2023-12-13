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
    public void addIngredient(Ingredient ingredient){
        boolean doesContain = false;
        for (int i = 0; i < fridgeList.size(); i++) {
            if(ingredient.getName().equals(fridgeList.get(i).getName())){
                doesContain = true;
                fridgeList.get(i).updateAmount(ingredient.getAmount());
                break;
            }
        }
        if(!doesContain){
            fridgeList.add(ingredient);
        }
    }

    public void removeIngredient(Ingredient ingredient){
        boolean doesContain = false;
        for (int i = 0; i < fridgeList.size(); i++) {
            if(ingredient.getName().equals(fridgeList.get(i).getName())){
                doesContain = true;
                fridgeList.get(i).updateAmount(-ingredient.getAmount());
                break;
            }
        }
    }

    public ArrayList<Ingredient> getSpecifiedTypeOfIngredient(String typeName){
        ArrayList<Ingredient> typeList = new ArrayList<Ingredient>();
        for (int i = 0; i < fridgeList.size(); i++) {
            if (fridgeList.get(i).getCategory().equalsIgnoreCase(typeName)) {
                typeList.add(fridgeList.get(i));
            }
        }
        return typeList;
    }


}
