package com.example.recipeasy;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Recipe;

import java.util.ArrayList;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    ArrayList<Recipe> recipes;
    Context context;
    public RecipeAdapter(Context context, ArrayList<Recipe> recipes){
        this.context = context;
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
        int drawableResourceId;
        if(Controller.findMissingIngredients(recipes.get(position)) == null){
            holder.missing.setText("You have all the ingredients");
            drawableResourceId = R.drawable.green_recipe_background;
        }
        else {
            holder.missing.setText("You have " + Controller.findMissingIngredients(recipes.get(position)).size() + " missing ingredients");
            drawableResourceId = R.drawable.red_recipe_background;
        }
        holder.background.setBackgroundResource(drawableResourceId);
        String imageUrl = recipes.get(position).getImageURL();
        Picasso.get().load(imageUrl).into(holder.imageView);
        
        int heartDrawable = recipes.get(position).isInFavorites() ? R.drawable.favorite_icon : R.drawable.favorite_icibos;
        holder.favorite.setImageResource(heartDrawable);

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newHeartDrawable;
                if(recipes.get(position).isInFavorites()){
                    Controller.getFavorites().removeRecipe(recipes.get(position));
                    newHeartDrawable = R.drawable.favorite_icibos;
                }
                else{
                    Controller.getFavorites().addRecipe(recipes.get(position));
                    newHeartDrawable = R.drawable.favorite_icon;
                }
                // Update the heart button drawable
                //int newHeartDrawable = recipes.get(position).isInFavorites() ? R.drawable.favorite_icibos : R.drawable.favorite_icon;
                holder.favorite.setImageResource(newHeartDrawable);
            }
        });

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
        private ConstraintLayout background;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.recipeImage);
            title = itemView.findViewById(R.id.recipeName);
            servings = itemView.findViewById(R.id.recipeServings);
            time = itemView.findViewById(R.id.recipeTime);
            missing = itemView.findViewById(R.id.recipeMissIng);
            favorite = itemView.findViewById(R.id.recipes_favorites_button);
            imageView = itemView.findViewById(R.id.recipeImage);
            background = itemView.findViewById(R.id.recipeLayout1);

        }

    }

    public void setFilteredList(ArrayList<Recipe> filteredList){
        this.recipes = filteredList;
    }
}