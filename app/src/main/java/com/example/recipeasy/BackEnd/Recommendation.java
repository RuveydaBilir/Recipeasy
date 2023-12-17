package com.example.recipeasy.BackEnd;
import com.example.recipeasy.Controller;

import java.util.ArrayList;


public class Recommendation {
    private ArrayList<Recipe> recipes;

    public Recommendation() {
        recipes = Controller.getAllRecipes();
    }

    public void filterServings(ArrayList<Integer> servings) {
        ArrayList<Recipe> allRecipes = Controller.getAllRecipes();
        recipes = new ArrayList<>();
        if(servings.isEmpty()){
            recipes = Controller.getAllRecipes();
            return;
        }
        for (int i = 0; i < allRecipes.size(); i++) {
            for (int j = 0; j < servings.size(); j++) {
                if(allRecipes.get(i).getServings() == servings.get(j)){
                    recipes.add(allRecipes.get(i));
                    break;
                }
            }
        }
    }
    public void filterTime(ArrayList<Integer> time){
        ArrayList<Recipe> allRecipes = Controller.getAllRecipes();
        recipes = new ArrayList<>();
        if(time.isEmpty()){
            recipes = Controller.getAllRecipes();
            return;
        }
        for (int i = 0; i < allRecipes.size(); i++) {
            for (int j = 0; j < time.size(); j++) {
                if(allRecipes.get(i).getCookingTime()>=time.get(j)&&allRecipes.get(i).getCookingTime()< time.get(j)+15){
                    recipes.add(allRecipes.get(i));
                    break;
                }
            }
        }
    }

    public void filter (ArrayList<Integer> time, ArrayList<Integer> servings) {
        if (time.isEmpty() && servings.isEmpty()) {
            recipes = new ArrayList<>();
            recipes = Controller.getAllRecipes();
            mainSort();
        } else if (servings.isEmpty()) {
            filterTime(time);
        } else if (time.isEmpty()) {
            filterServings(servings);
        } else {
            ArrayList<Recipe> allRecipes = Controller.getAllRecipes();
            recipes = new ArrayList<>();
            for (int i = 0; i < allRecipes.size(); i++) {
                boolean servingsMatch = servings.isEmpty();
                boolean timeMatch = time.isEmpty();

                for (int j = 0; j < servings.size(); j++) {
                    if (allRecipes.get(i).getServings() == servings.get(j)) {
                        servingsMatch = true;
                        break;
                    }
                }

                for (int j = 0; j < time.size(); j++) {
                    if (allRecipes.get(i).getCookingTime() >= time.get(j) && allRecipes.get(i).getCookingTime() < time.get(j) + 15) {
                        timeMatch = true;
                        break;
                    }
                }

                if (servingsMatch && timeMatch) {
                    recipes.add(allRecipes.get(i));
                }
            }
        }
        mainSort();
    }

    public void sortServings(boolean ascending){
        quickSortForServings(0, recipes.size() - 1, ascending);
    }
    public void sortTime(boolean ascending){
        quickSortForTime(0, recipes.size() - 1, ascending);
    }
    public void mainSort(){
//        recipes = Controller.getAllRecipes();
        quickSortMain(0, recipes.size()-1);
    }
    private void quickSortForServings(int low, int high, boolean ascendingOrder) {
        if (low < high) {
            int pivotIndex = partitionForServings(low, high, ascendingOrder);
            quickSortForServings(low, pivotIndex - 1, ascendingOrder);
            quickSortForServings(pivotIndex + 1, high, ascendingOrder);
        }
    }

    private int partitionForServings(int low, int high, boolean ascending) {
        Recipe pivot = recipes.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Sort only if missingNum is the same
            if (Controller.findMissingIngredients(recipes.get(j)).size() == Controller.findMissingIngredients(pivot).size()) {
                if ((ascending && recipes.get(j).getServings() <= pivot.getServings()) ||
                        (!ascending && recipes.get(j).getServings() >= pivot.getServings())) {
                    i++;
                    swap(i, j);
                }
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void quickSortForTime(int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partitionForTime(low, high, ascending);
            quickSortForTime(low, pi - 1, ascending);
            quickSortForTime(pi + 1, high, ascending);
        }
    }
    private int partitionForTime(int low, int high, boolean ascending) {
        Recipe pivot = recipes.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (Controller.findMissingIngredients(recipes.get(j)).size() == Controller.findMissingIngredients(pivot).size()) {
                if ((ascending && recipes.get(j).getCookingTime() <= pivot.getCookingTime()) ||
                        (!ascending && recipes.get(j).getCookingTime() >= pivot.getCookingTime())) {
                    i++;
                    // Swap recipes[i] and recipes[j]
                    swap(i,j);
                }
            }
        }

        // Swap recipes[i + 1] and recipes[high] (or pivot)
        swap(i+1,high);
        return i + 1;
    }

    private void quickSortMain(int low, int high) {
        if (low < high) {
            int pi = partitionForMain(low, high);
            quickSortMain(low, pi - 1);
            quickSortMain(pi + 1, high);
        }
    }
    private int partitionForMain(int low, int high) {
        Recipe pivot = recipes.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(Controller.findMissingIngredients(recipes.get(j)) != null && Controller.findMissingIngredients(pivot)!=null ){
                if (Controller.findMissingIngredients(recipes.get(j)).size() <= Controller.findMissingIngredients(pivot).size()) {
                    i++;
                    swap(i,j);
                }
            }

        }
        swap(i+1,high);
        return i + 1;
    }

    private void swap(int i, int j) {

        Recipe temp = recipes.get(i);
        recipes.set(i, recipes.get(j));
        recipes.set(j, temp);
    }


    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
        }

}




