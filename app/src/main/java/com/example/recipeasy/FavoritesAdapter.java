package com.example.recipeasy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    ArrayList<Recipe> favorites;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    public FavoritesAdapter(Context context,ArrayList<Recipe> favorites, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.favorites = favorites;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public FavoritesAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recipes, parent, false);
        return new FavoritesAdapter.FavoritesViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavoritesViewHolder holder, int position) {
        holder.title.setText(favorites.get(position).getName());
        holder.time.setText(String.valueOf(favorites.get(position).getCookingTime()));
        holder.servings.setText(String.valueOf(favorites.get(position).getServings()));
        int drawableResourceId;
        if(Controller.findMissingIngredients(favorites.get(position)) == null || Controller.findMissingIngredients(favorites.get(position)).size() == 0){
            holder.missing.setText("You have all the ingredients");
            drawableResourceId = R.drawable.green_recipe_background;
        }
        else {
            holder.missing.setText("You have " + Controller.findMissingIngredients(favorites.get(position)).size() + " missing ingredients");
            drawableResourceId = R.drawable.red_recipe_background;
        }
        holder.background.setBackgroundResource(drawableResourceId);
        String imageUrl = favorites.get(position).getImageURL();
        Picasso.get().load(imageUrl).into(holder.imageView);

        int heartDrawable = favorites.get(position).inFavorites() ? R.drawable.favorite_icon_kirmizi : R.drawable.favorite_icibos;
        holder.favorite.setImageResource(heartDrawable);

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newHeartDrawable;
                if(favorites.get(position).inFavorites()){
                    Controller.getFavorites().removeRecipe(favorites.get(position));
                    newHeartDrawable = R.drawable.favorite_icibos;
                    notifyItemChanged(position, null);
                }
                else{
                    Controller.getFavorites().addRecipe(favorites.get(position));
                    newHeartDrawable = R.drawable.favorite_icon_kirmizi;
                }
                // Update the heart button drawable
                //int newHeartDrawable = recipes.get(position).isInFavorites() ? R.drawable.favorite_icibos : R.drawable.favorite_icon;
                holder.favorite.setImageResource(newHeartDrawable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;
        private TextView servings;
        private TextView time;
        private TextView missing;
        private ImageButton favorite;
        private ConstraintLayout background;
        public FavoritesViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            title = itemView.findViewById(R.id.recipeName);
            servings = itemView.findViewById(R.id.recipeServings);
            time = itemView.findViewById(R.id.recipeTime);
            missing = itemView.findViewById(R.id.recipeMissIng);
            favorite = itemView.findViewById(R.id.recipes_favorites_button);
            imageView = itemView.findViewById(R.id.recipeImage);
            background = itemView.findViewById(R.id.recipeLayout1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
