package com.example.recipeasy.BackEnd;

import com.example.recipeasy.Controller;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public void addIngredient(Ingredient ingredient){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("ShoppingList").child("shoppingList");
        boolean doesContain = false;
        for (int i = 0; i < shoppingList.size(); i++) {
            if(ingredient.getName().equals(shoppingList.get(i).getName())){
                doesContain = true;
                int newAmount = shoppingList.get(i).updateAmount(ingredient.getAmount());
                reference.child(Integer.toString(i)).child("amount").setValue(newAmount);
                break;
            }
        }
        if(!doesContain){
            reference.setValue(ingredient);
        }
    }

    public void removeIngredient(Ingredient ingredient){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("ShoppingList").child("shoppingList");
        boolean doesContain = false;
        for (int i = 0; i < shoppingList.size(); i++) {
            if(ingredient.getName().equals(shoppingList.get(i).getName())){
                doesContain = true;
                int newAmount = shoppingList.get(i).updateAmount(-ingredient.getAmount());
                reference.child(Integer.toString(i)).child("amount").setValue(newAmount);                break;
            }
        }
    }

    public ArrayList<Ingredient> getSpecifiedTypeOfIngredient(String typeName){
        ArrayList<Ingredient> typeList = new ArrayList<Ingredient>();
        for (int i = 0; i < shoppingList.size(); i++) {
            if (shoppingList.get(i).getCategory().equalsIgnoreCase(typeName)) {
                typeList.add(shoppingList.get(i));
            }
        }
        return typeList;
    }
}
