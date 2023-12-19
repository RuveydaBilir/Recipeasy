package com.example.recipeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;

import java.util.ArrayList;

public class FridgeIngredientInCategoryAdapter extends RecyclerView.Adapter<com.example.recipeasy.FridgeIngredientInCategoryAdapter.MyViewHolder> {

    ArrayList<Ingredient> ingredients ;
    Context context;



    public FridgeIngredientInCategoryAdapter(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;

    }

    @NonNull
    @Override
    public FridgeIngredientInCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fridge_ingredient_in_category_card_view, parent, false);
        return new FridgeIngredientInCategoryAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FridgeIngredientInCategoryAdapter.MyViewHolder holder, int position) {
        holder.name.setText(ingredients.get(position).getName());
        holder.amount.setText(Double.toString(ingredients.get(position).getAmount()));
        holder.type.setText(ingredients.get(position).getMeasureType());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView amount;
        private TextView type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredient_name);
            amount = itemView.findViewById(R.id.ingredient_amount);
            type = itemView.findViewById(R.id.ingredient_amount_type);


        }

    }
}
