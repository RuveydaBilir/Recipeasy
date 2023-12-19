package com.example.recipeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FridgeIngredientInCategoryAdapter extends RecyclerView.Adapter<com.example.recipeasy.FridgeIngredientInCategoryAdapter.MyViewHolder> {

    ArrayList<String> category;
    Context context;



    public FridgeIngredientInCategoryAdapter(Context context, ArrayList<String> category) {
        this.context = context;
        this.category = category;

    }

    @NonNull
    @Override
    public FridgeIngredientInCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fridge_recycler_view_layout, parent, false);
        return new FridgeIngredientInCategoryAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FridgeIngredientInCategoryAdapter.MyViewHolder holder, int position) {
        holder.categoryName.setText(category.get(position));
        holder.categoryNum.setText(String.valueOf(Controller.getFridge().getSpecifiedTypeOfIngredient(category.get(position)).size()));
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView categoryName;
        private TextView categoryNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView);
            categoryName = itemView.findViewById(R.id.textView3);
            categoryNum = itemView.findViewById(R.id.ingredient_group_count);


        }

    }
}
