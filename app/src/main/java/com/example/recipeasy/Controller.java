package com.example.recipeasy;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.recipeasy.BackEnd.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private static User user;
    private static Fridge fridge;
    private static Favorites favorites;
    private static Planner planner;
    private static ShoppingList shoppingList;
    private static Recommendation recommendation;

    private static ArrayList<Recipe> allRecipes = new ArrayList<>();
    private static ArrayList<Ingredient> allIngredients = new ArrayList<>();
    public static ArrayList<String> categories = new ArrayList<>();

    public Controller() {
        Controller.user = new User();
        Controller.fridge = new Fridge();
        Controller.favorites = new Favorites();
        Controller.planner = new Planner();
        Controller.shoppingList = new ShoppingList();
        Controller.recommendation = new Recommendation();
        Controller.categories = new ArrayList<>();
        setAllIngredients();
        setAllRecipes();
        setCategories();
    }

    public void setCategories(){
        categories.add("Vegetables");
        categories.add("Fruits");
        categories.add("Fish & Marine Products");
        categories.add("Dairy Products");
        categories.add("Legumes");
        categories.add("Canned Products");
        categories.add("Nuts");
        categories.add("Meat Products");
        categories.add("Oils");
    }

    public Controller(User user, Fridge fridge, Favorites favorites, Planner planner, ShoppingList shoppingList, Recommendation recommendation) {
        Controller.user = user;
        Controller.fridge = fridge;
        Controller.favorites = favorites;
        Controller.planner = planner;
        Controller.shoppingList = shoppingList;
        Controller.recommendation = recommendation;
        Controller.categories = new ArrayList<>();
        setCategories();
    }

    public static void createUserData(String userID) {
        FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Fridge").child("fridgeList").setValue(0);
        FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Favorites").child("recipes").setValue(0);
        FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Planner").child("recipes").setValue(0);
        FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Shopping List").child("shoppingList").setValue(0);
        FirebaseDatabase.getInstance().getReference("Users").child(userID).child("Recommendation").child("recipes").setValue(0);
    }

    public static void addRecipe(Recipe recipe) {
        FirebaseDatabase.getInstance().getReference("Recipes").child(recipe.getName()).setValue(recipe);
    }

    public static void addIngredients(ArrayList<Ingredient> ingredients, DatabaseReference reference) {
        for (Ingredient ingredient : ingredients) {
            reference.child(ingredient.getName()).setValue(ingredient);
        }
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

    public static ArrayList<Ingredient> findMissingIngredients(Recipe recipe){
        ArrayList<Ingredient> missingList = new ArrayList<Ingredient>();
        //checks for each ingredient in recipe
        for (int i = 0; i <recipe.getIngredients().size(); i++) {
        Ingredient recipeIngredient = recipe.getIngredients().get(i);
            boolean foundInFridge = false;
            //check for ingredient in fridge
            for (int j = 0; j < fridge.getFridgeList().size(); j++) {
                Ingredient fridgeIngredient = fridge.getFridgeList().get(j);
                //checks whether the fridge includes that ingredient
                if(recipeIngredient.getName().equals(fridgeIngredient.getName())){
                    foundInFridge = true;
                    //adds if recipe needs more amount of that ingredient
                    if (recipeIngredient.getAmount()>fridgeIngredient.getAmount()){
                        Ingredient addingIngredient = new Ingredient(recipeIngredient.getName(), recipeIngredient.getMeasureType(), recipeIngredient.getCategory(), recipeIngredient.getAmount()-fridgeIngredient.getAmount());
                        missingList.add(addingIngredient);
                    }
                }
            }
            // Add if user does not have that ingredient
            if (!foundInFridge) {
                missingList.add(recipeIngredient);
            }
        }
        return missingList;
    }

    public static Recipe chooseFromFavorites(){
        Random random = new Random();
        int rand_int1 = random.nextInt(favorites.getRecipes().size());
        Recipe r = favorites.getRecipes().get(rand_int1);
        return r;
    }

    /*public boolean searchRecipe(String name){
        for (int i = 0; i < recommendation.getRecipes().size(); i++) {
            if(recommendation.getRecipes().get(i).getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }*/

    public static void updateShoppingList(Ingredient ingredient){
        for (int i = 0; i < shoppingList.getShoppingList().size(); i++) {
            if (ingredient.getName().equalsIgnoreCase(shoppingList.getShoppingList().get(i).getName())) {
                if(ingredient.getAmount()>shoppingList.getShoppingList().get(i).getAmount())
                    shoppingList.getShoppingList().get(i).updateAmount(ingredient.getAmount()-shoppingList.getShoppingList().get(i).getAmount());
            }
            else {
                shoppingList.getShoppingList().add(ingredient);
            }
        }
    }

    /*public void createMealPlanner(){
        int[] intOfRecipeIndex = new int[7];
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(recommendation.getRecipes().size());
            } while (!contains(intOfRecipeIndex, randomIndex));

            intOfRecipeIndex[i] = randomIndex;
        }
        for (int i = 0; i < 7; i++) {
            planner.getRecipes()[i] = recommendation.getRecipes().get(intOfRecipeIndex[i]);
        }
    }*/

    private static boolean contains(int[] array, int value) {
        for (int element : array) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUserSignedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    private static void setAllRecipes() {
        FirebaseDatabase.getInstance().getReference("Recipes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allRecipes.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Recipe recipe = snap.getValue(Recipe.class);
                    allRecipes.add(recipe);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private static void setAllIngredients() {
        FirebaseDatabase.getInstance().getReference("Ingredients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allIngredients.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Ingredient ingredient = snap.getValue(Ingredient.class);
                    allIngredients.add(ingredient);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public static void setUserData() {
    /*setFridge();
    setFavorites();
    setPlanner();
    setShoppingList();
    setRecommendation();*/
    }

    public static ArrayList<Recipe> getAllRecipes() {
        return allRecipes;
    }

    public static ArrayList<Ingredient> getAllIngredients() {
        return allIngredients;
    }
}
