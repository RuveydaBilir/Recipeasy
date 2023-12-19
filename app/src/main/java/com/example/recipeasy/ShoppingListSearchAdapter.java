package com.example.recipeasy;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;

import java.util.ArrayList;

public class ShoppingListSearchAdapter extends RecyclerView.Adapter<com.example.recipeasy.ShoppingListSearchAdapter.MyViewHolder>{
    ArrayList<Ingredient> ingredients;
    Context context;

    public ShoppingListSearchAdapter(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ShoppingListSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.search_bar_fridge_ingredient, parent, false);
        return new ShoppingListSearchAdapter.MyViewHolder(view);

    }
    public void setFilteredList(ArrayList<Ingredient> filteredList){
        this.ingredients = filteredList;
        //notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListSearchAdapter.MyViewHolder holder, int position) {
        holder.ingredientName.setText(ingredients.get(position).getName());
        holder.ingredientNum.setText(Double.toString(ingredients.get(position).getAmount()));
        holder.ingredientAmountType.setText(ingredients.get(position).getMeasureType());
        holder.ingredientNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)) {
                    return;
                }

                String amountString = s.toString();

                amountString = amountString.replace(".","");
                double newAmount = (Double.valueOf(amountString))/10;

                if(ingredients.get(holder.getAdapterPosition()).getAmount() == newAmount) {
                    return;
                }

                if(newAmount >= 0){

                    Ingredient ingredient = ingredients.get(holder.getAdapterPosition());
                    ingredient.setAmount(newAmount);
                    Controller.getShoppingList().setIngredient(ingredient);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView ingredientName;
        private EditText ingredientNum;
        private TextView ingredientAmountType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientNum = itemView.findViewById(R.id.ingredient_amount);
            ingredientAmountType = itemView.findViewById(R.id.ingredient_amount_type);
        }

    }
}
