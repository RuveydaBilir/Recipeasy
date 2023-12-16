package com.example.recipeasy.BackEnd;

import com.example.recipeasy.Controller;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Fridge {
    private ArrayList<Ingredient> fridgeList;

    /**
     * Constructs a fridge Arraylist containing every possible ingredient
     */
    public Fridge() {
        fridgeList = new ArrayList<>();
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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Fridge").child("fridgeList");
        boolean doesContain = false;
        for (int i = 0; i < fridgeList.size(); i++) {
            if(ingredient.getName().equals(fridgeList.get(i).getName())){
                doesContain = true;
                double newAmount = fridgeList.get(i).updateAmount(ingredient.getAmount());
                reference.child("" + i).child("amount").setValue(newAmount);
                break;
            }
        }
        if(!doesContain){
            reference.child("" + fridgeList.size()).setValue(ingredient);
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
