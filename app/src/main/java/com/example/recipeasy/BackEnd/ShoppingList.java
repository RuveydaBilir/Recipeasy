package com.example.recipeasy.BackEnd;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.recipeasy.Controller;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> shoppingList;

    /**
     * constructs a basket arraylist containing every possible ingredient
     */
    public ShoppingList() {
        shoppingList = new ArrayList<>();
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
        if(ingredient.getAmount() <= 0) {
            return;
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Shopping List").child("shoppingList");
        Query query = reference.orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    if(snap.getValue(Ingredient.class).getName().equals(ingredient.getName())) {
                        double newAmount = snap.getValue(Ingredient.class).updateAmount(ingredient.getAmount());

                        if(newAmount == 0) {
                            reference.child(snap.getKey()).removeValue();

                        }
                        else {
                            reference.child(snap.getKey()).child("amount").setValue(newAmount);
                        }
                        return;
                    }
                }
                reference.push().setValue(ingredient);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void setIngredient(Ingredient ingredient) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Shopping List").child("shoppingList");
        Query query = reference.orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {

                    if(snap.getValue(Ingredient.class).getName().equals(ingredient.getName())) {
                        if(ingredient.getAmount() == 0.0) {
                            Log.d("message", "REMOVED");
                            reference.child(snap.getKey()).removeValue();
                            return;
                        }

                        reference.child(snap.getKey()).child("amount").setValue(ingredient.getAmount());
                        return;
                    }
                }
                reference.push().setValue(ingredient);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
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
