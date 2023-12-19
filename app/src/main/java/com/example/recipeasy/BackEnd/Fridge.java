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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Fridge").child("fridgeList");
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
        for (int i = 0; i < fridgeList.size(); i++) {
            if (fridgeList.get(i).getCategory().equalsIgnoreCase(typeName)) {
                typeList.add(fridgeList.get(i));
            }
        }
        return typeList;
    }



}
