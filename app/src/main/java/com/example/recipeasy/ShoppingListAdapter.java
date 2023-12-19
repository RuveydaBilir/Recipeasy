package com.example.recipeasy;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoopingListViewHolder> {
    ArrayList<Ingredient> shoppingList;
    Context context;

    @NonNull
    @Override
    public ShoppingListAdapter.ShoopingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ShoopingListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ShoopingListViewHolder extends RecyclerView.ViewHolder {
    }
}
