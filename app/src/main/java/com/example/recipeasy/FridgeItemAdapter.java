package com.example.recipeasy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.Recipe;

import java.util.ArrayList;

public class FridgeItemAdapter extends RecyclerView.Adapter<FridgeItemAdapter.FridgeItemViewHolder> {

    private ArrayList<Ingredient> ingredients;
    public FridgeItemAdapter(ArrayList<Ingredient> ingredients){this.ingredients = ingredients;}

    public void setFilteredList(ArrayList<Ingredient> filteredList){
        this.ingredients = filteredList;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FridgeItemAdapter.FridgeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_bar_fridge_ingredient, parent, false);
        return new FridgeItemAdapter.FridgeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeItemAdapter.FridgeItemViewHolder holder, int position) {
        holder.ingredientName.setText(ingredients.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class FridgeItemViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientName;
        public FridgeItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName= itemView.findViewById(R.id.ingredient_name);
        }
    }
}
