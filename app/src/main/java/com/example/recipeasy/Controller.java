package com.example.recipeasy;

import androidx.annotation.NonNull;

import com.example.recipeasy.BackEnd.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Controller {

    private static User user;
    private static Fridge fridge;
    private static Favorites favorites;
    private static Planner planner;
    private static ShoppingList shoppingList;
    private static Recommendation recommendation;

    public Controller() {
    }

    public Controller(User user, Fridge fridge, Favorites favorites, Planner planner, ShoppingList shoppingList, Recommendation recommendation) {
        Controller.user = user;
        Controller.fridge = fridge;
        Controller.favorites = favorites;
        Controller.planner = planner;
        Controller.shoppingList = shoppingList;
        Controller.recommendation = recommendation;
    }

    public static void signIn(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()) {
                  //TODO: Update the UI accordingly
                  //TODO: Go to the main page
              }
              else {
                  //TODO: Display a sign in failure message
              }
            }
        });
    }

    public static void signUp(String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //If signed up successfully
                if(task.isSuccessful()) {
                    //TODO: Update the UI accordingly
                    //TODO: Go to sign in page
                }
                else {
                    //TODO: Display a sign up failure message
                }
            }
        });
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Controller.user = user;
    }

    public static Fridge getFridge() {
        return fridge;
    }

    public static void setFridge(Fridge fridge) {
        Controller.fridge = fridge;
    }

    public static Favorites getFavorites() {
        return favorites;
    }

    public static void setFavorites(Favorites favorites) {
        Controller.favorites = favorites;
    }

    public static Planner getPlanner() {
        return planner;
    }

    public static void setPlanner(Planner planner) {
        Controller.planner = planner;
    }

    public static ShoppingList getShoppingList() {
        return shoppingList;
    }

    public static void setShoppingList(ShoppingList shoppingList) {
        Controller.shoppingList = shoppingList;
    }

    public static Recommendation getRecommendation() {
        return recommendation;
    }

    public static void setRecommendation(Recommendation recommendation) {
        Controller.recommendation = recommendation;
    }
}
