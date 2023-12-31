package com.example.recipeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import com.example.recipeasy.BackEnd.Recipe;

import java.util.ArrayList;

public class WeeklyPlannerAdapter extends RecyclerView.Adapter<WeeklyPlannerAdapter.WeeklyViewHolder> {

    ArrayList<String> dayOfWeek;
    ArrayList<Recipe> weeklyRecipes;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public WeeklyPlannerAdapter(Context context, ArrayList<Recipe> weeklyRecipes, RecyclerViewInterface recyclerViewInterface){
        this.recyclerViewInterface = recyclerViewInterface;
        this.weeklyRecipes = weeklyRecipes;
        this.context = context;
        setDayOfWeek();
    }

    private void setDayOfWeek(){
        dayOfWeek = new ArrayList<>();
        dayOfWeek.add("Monday");
        dayOfWeek.add("Tuesday");
        dayOfWeek.add("Wednesday");
        dayOfWeek.add("Thursday");
        dayOfWeek.add("Friday");
    }

    @NonNull
    @Override
    public WeeklyPlannerAdapter.WeeklyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.weekly_planner_recycler_view_layout, parent, false);
        return new WeeklyPlannerAdapter.WeeklyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyPlannerAdapter.WeeklyViewHolder holder, int position) {
        holder.title.setText(weeklyRecipes.get(position).getName());
        holder.time.setText(String.valueOf(weeklyRecipes.get(position).getCookingTime()) + " min");
        holder.servings.setText("Serve " + String.valueOf(weeklyRecipes.get(position).getServings()));
        holder.day.setText(dayOfWeek.get(position));
        int drawableResourceId;
        if(Controller.findMissingIngredients(weeklyRecipes.get(position)) == null){
            holder.missing.setText("You have all the ingredients");
            drawableResourceId = R.drawable.green_recipe_background;
        }
        else {
            holder.missing.setText(Controller.findMissingIngredients(weeklyRecipes.get(position)).size() + " missing ingredients");
            drawableResourceId = R.drawable.red_recipe_background;
        }
        holder.background.setBackgroundResource(drawableResourceId);
        String imageUrl = weeklyRecipes.get(position).getImageURL();
        Picasso.get().load(imageUrl).into(holder.imageView);
        holder.chooseFromFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newHeartDrawable;
                if(Controller.chooseFromFavorites() == null){
                    return;
                }
                weeklyRecipes.set(position, Controller.chooseFromFavorites());
                notifyDataSetChanged();
            }
        });

        holder.randomRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomInt = (int)(Controller.getAllRecipes().size()*Math.random()-1);
                weeklyRecipes.set(position, Controller.getAllRecipes().get(randomInt));
                Recipe[] recipeArr = new Recipe[5];
                for (int i = 0; i <weeklyRecipes.size() ; i++) {
                    recipeArr[i] = weeklyRecipes.get(i);
                }
                Controller.getPlanner().setRecipes(recipeArr);
                notifyDataSetChanged();
            }
        });

        if(Controller.findMissingIngredients(weeklyRecipes.get(position)) == null || Controller.findMissingIngredients(weeklyRecipes.get(position)).size() == 0){
            holder.missing.setText("You have all the ingredients");
            drawableResourceId = R.drawable.green_recipe_background;
        }
        else {
            holder.missing.setText("You have " + Controller.findMissingIngredients(weeklyRecipes.get(position)).size() + " missing ingredients");
            drawableResourceId = R.drawable.red_recipe_background;
        }
        holder.background.setBackgroundResource(drawableResourceId);


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class WeeklyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView servings;
        private TextView time;
        private TextView missing;
        private ConstraintLayout background;
        private TextView day;
        private Button chooseFromFavorites;
        private Button randomRecipe;
        public WeeklyViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.recipeNameWL);
            servings = view.findViewById(R.id.recipeServingsWL);
            time = view.findViewById(R.id.recipeTimeWL);
            missing = view.findViewById(R.id.recipeMissIngWL);
            day = view.findViewById(R.id.day_of_the_week);
            background = view.findViewById(R.id.background);
            imageView = view.findViewById(R.id.recipeImageWL);
            chooseFromFavorites = view.findViewById(R.id.choose_from_favorites);
            randomRecipe = view.findViewById(R.id.get_another_recommendation);

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
