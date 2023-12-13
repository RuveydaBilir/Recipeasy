package com.example.recipeasy.BackEnd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.Controller;
import com.example.recipeasy.R;

import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    ArrayList<Recipe> recipes;
    Context context;
    public RecipeAdapter(Context context){
        this.context = context;
        this.recipes = Controller.getRecommendation().getRecipes();
    }

    @NonNull
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(R.layout.recipes, parent, false);
        return new RecipeAdapter.MyViewHolder(view);
    }

    
    
    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder holder, int position) {
        holder.title.setText(recipes.get(position).getName());
        holder.time.setText(recipes.get(position).getCookingTime());
        holder.servings.setText(recipes.get(position).getServings());
        if(recipes.get(position).getMissingNum() == 0){
            holder.missing.setText("You have all the ingredients");
        }
        else {
            holder.missing.setText("You have " + recipes.get(position).getMissingNum() + " missing ingredients");
        }
        recipes.get(position).getRecipeImage();
        //holder.imageView.set;
    }

    @Override
    public int getItemCount() {

        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView servings;
        TextView time;
        TextView missing;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recipeImage);
            title = itemView.findViewById(R.id.recipeName);
            servings = itemView.findViewById(R.id.recipeServings);
            time = itemView.findViewById(R.id.recipeTime);
            missing = itemView.findViewById(R.id.recipeMissIng);


        }
    }
}