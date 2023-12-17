package com.example.recipeasy.BackEnd;

import android.provider.ContactsContract;
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

public class Favorites {

    private ArrayList<Recipe> recipes;

    public Favorites() {
        recipes = new ArrayList<>();
    }

    public Favorites(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Favorites").child("recipes");
        boolean doesContain = false;
        for (int i = 0; i < recipes.size(); i++) {
            if(recipe.getName().equals(recipes.get(i).getName())){
                doesContain = true;
                break;
            }
        }
        if(!doesContain){
            reference.push().setValue(recipe);
        }
    }

    public void removeRecipe(Recipe recipe){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(Controller.getUser().getUserID()).child("Favorites").child("recipes");
        Query query = reference.orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    if(snap.getValue(Recipe.class).getName().equals(recipe.getName())) {
                        reference.child(snap.getKey()).removeValue();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
