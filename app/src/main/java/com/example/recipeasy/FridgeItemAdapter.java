package com.example.recipeasy;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Fridge;
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
        holder.ingredientName.setText(ingredients.get(holder.getAdapterPosition()).getName());
        holder.ingredientAmount.setText(String.valueOf(ingredients.get(holder.getAdapterPosition()).getAmount()));
        holder.ingredientAmountType.setText(String.valueOf(ingredients.get(holder.getAdapterPosition()).getMeasureType()));

        holder.ingredientAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String amountString = s.toString();
                if(amountString.equals("0.0")) {
                    return;
                }

                amountString = amountString.replace(".","");
                double newAmount = (Double.valueOf(amountString))/10;

                if(ingredients.get(holder.getAdapterPosition()).getAmount() == newAmount) {
                    return;
                }
                if(newAmount >= 0){
                    Ingredient ingredient = ingredients.get(holder.getAdapterPosition());
                    ingredient.setAmount(newAmount);
                    Log.d("AMOUNT: ", " " + ingredient.getAmount() + " " + ingredient.getName());
                    Controller.getFridge().setIngredient(ingredient);
                    ingredients.set(holder.getAdapterPosition(), ingredient);
                    //notifyDataSetChanged();
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
                holder.ingredientAmountType.setText(String.valueOf(ingredients.get(holder.getAdapterPosition()).getMeasureType()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class FridgeItemViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientName;
        private TextView ingredientAmount;
        private TextView ingredientAmountType;
        public FridgeItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName= itemView.findViewById(R.id.ingredient_name);
            ingredientAmount = itemView.findViewById(R.id.ingredient_amount);
            ingredientAmountType = itemView.findViewById(R.id.ingredient_amount_type);
        }
    }
}
