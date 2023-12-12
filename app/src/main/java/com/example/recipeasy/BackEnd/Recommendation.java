package com.example.recipeasy.BackEnd;
import java.util.ArrayList;


public class Recommendation {
    private ArrayList<Recipe> recipes;

    public Recommendation() {
    }

    public Recommendation(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void filterServings(ArrayList<Integer> servings){
        for (int i = 0; i < recipes.size(); i++) {
            for (int j = 0; j < servings.size(); j++) {
                recipes.get(i).setShown(false);
                if(recipes.get(i).getServings()==servings.get(j)){
                    recipes.get(i).setShown(true);
                    break;
                }
            }
        }
    }
    public void filterTime(ArrayList<Integer> time){
        for (int i = 0; i < recipes.size(); i++) {
            for (int j = 0; j < time.size(); j++) {
                recipes.get(i).setShown(false);
                if(recipes.get(i).getServings()==time.get(j)){
                    recipes.get(i).setShown(true);
                    break;
                }
            }
        }
    }
    public void sortServings(boolean ascending){
        quickSortForServings(0, recipes.size() - 1, ascending);
    }
    public void sortTime(boolean ascending){
        quickSortForTime(0, recipes.size() - 1, ascending);
    }
    public void mainSort(){
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
            if (recipes.get(j).getMissingNum() == pivot.getMissingNum()) {
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
            // Check if the current element's missingNum is the same as the pivot's
            if (recipes.get(j).getMissingNum() == pivot.getMissingNum()) {
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
            if(recipes.get(j).getMissingNum() <= pivot.getMissingNum()){
                i++;
                swap(i,j);
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
