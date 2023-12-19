package com.example.recipeasy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.Recipe;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {
    Context context;
    ArrayList<Ingredient> ingredients;
    ArrayList<Ingredient> missing;
    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients, ArrayList<Ingredient> missing ){
        this.context = context;
        this.ingredients = ingredients;
        this.missing = missing;
    }

    @NonNull
    @Override
    public IngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient, parent, false);
        return new IngredientAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.MyViewHolder holder, int position) {
        holder.name.setText(ingredients.get(position).getName());
        holder.amount.setText(Double.toString(ingredients.get(position).getAmount()) + ingredients.get(position).getMeasureType());
        if(missing != null){
            boolean isMissing = false;
            for(int i = 0; i < missing.size(); i++){
                if(missing.get(i).getName().equalsIgnoreCase(ingredients.get(position).getName())){
                    isMissing = true;
                }
            }
            if(!isMissing){
                holder.name.setTextColor(ContextCompat.getColor(context,R.color.yesil_malzeme));
                holder.amount.setTextColor(ContextCompat.getColor(context,R.color.yesil_malzeme));
                holder.button.setVisibility(View.GONE);
            }
            else{
                holder.name.setTextColor(ContextCompat.getColor(context,R.color.kirmizi_malzeme));
                holder.amount.setTextColor(ContextCompat.getColor(context,R.color.kirmizi_malzeme));
                holder.button.setVisibility(View.VISIBLE);
            }
        }
        else{
            holder.name.setTextColor(ContextCompat.getColor(context,R.color.yesil_malzeme));
            holder.button.setVisibility(View.GONE);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.getShoppingList().addIngredient(ingredients.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView amount;
        ImageButton button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredientsName);
            button = itemView.findViewById(R.id.shoppingCartBtn);
            amount = itemView.findViewById(R.id.ingredientsAmount);
        }
    }
}
