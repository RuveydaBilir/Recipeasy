package com.example.recipeasy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Recipe;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private ArrayList<Recipe> recipes;
    public ItemAdapter(ArrayList<Recipe> recipes){this.recipes = recipes;}

    public void setFilteredList(ArrayList<Recipe> filteredList){
        this.recipes = filteredList;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_bar_recipe_recycler_view, parent, false);
        return new ItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        holder.recipeName.setText(recipes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeName;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName= itemView.findViewById(R.id.search_recipe_name3);
        }
    }


}
