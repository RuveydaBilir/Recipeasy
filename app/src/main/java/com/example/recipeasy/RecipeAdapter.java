package com.example.recipeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Recipe;

import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    ArrayList<Recipe> recipes;
    Context context;
    public RecipeAdapter(Context context, ArrayList<Recipe> recipes){
        this.context = context;
        //this.recipes = recipes;
        this.recipes = recipes;
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
        holder.time.setText(String.valueOf(recipes.get(position).getCookingTime()));
        holder.servings.setText(String.valueOf(recipes.get(position).getServings()));
        if(Controller.findMissingIngredients(recipes.get(position)) == null){
            holder.missing.setText("You have all the ingredients");
        }
        else {
            holder.missing.setText("You have " + Controller.findMissingIngredients(recipes.get(position)).size() + " missing ingredients");
        }
        //holder.favorite.
       // recipes.get(position).getRecipeImage();
        //holder.imageView.set;
    }

    @Override
    public int getItemCount() {

        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView servings;
        private TextView time;
        private TextView missing;
        private ImageButton favorite;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.recipeImage);
            title = itemView.findViewById(R.id.recipeName);
            servings = itemView.findViewById(R.id.recipeServings);
            time = itemView.findViewById(R.id.recipeTime);
            missing = itemView.findViewById(R.id.recipeMissIng);
            favorite = itemView.findViewById(R.id.recipes_favorites_button);


        }

    }
    public void setFilteredList(ArrayList<Recipe> filteredList){
        this.recipes = filteredList;
    }
}