package com.example.recipeasy;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;

import java.util.ArrayList;
import java.util.Locale;

public class FridgeAdapter extends RecyclerView.Adapter<com.example.recipeasy.FridgeAdapter.MyViewHolder> {
    ArrayList<String> category;
    Context context;

    private final RecyclerViewInterface recyclerViewInterface;

    public FridgeAdapter(Context context, ArrayList<String> category, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.category = category;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public FridgeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fridge_recycler_view_layout, parent, false);
        return new FridgeAdapter.MyViewHolder(view,recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull FridgeAdapter.MyViewHolder holder, int position) {
        holder.categoryName.setText(category.get(position));
        holder.categoryNum.setText(String.valueOf(Controller.getFridge().getSpecifiedTypeOfIngredient(category.get(position)).size()));
        holder.bind(category.get(position));
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView categoryName;
        private TextView categoryNum;
        private RecyclerView innerView;
        boolean visible = false;
        private CardView background;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            icon = itemView.findViewById(R.id.imageView);
            categoryName = itemView.findViewById(R.id.textView3);
            categoryNum = itemView.findViewById(R.id.ingredient_group_count);
            innerView = itemView.findViewById(R.id.fridge_ingredient_in_categories_recycler_view_layout);
            background = itemView.findViewById(R.id.category_card_view);
            innerView.setVisibility(View.GONE);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    } */
                    if(!visible){
                        innerView.setVisibility(View.VISIBLE);
                        background.setCardBackgroundColor(Color.rgb(255,226,226));
                        visible = true;
                    }
                    else{
                        innerView.setVisibility(View.GONE);
                        background.setCardBackgroundColor(Color.rgb(226,223,223));
                        visible = false;
                    }

                }
            });
        }
            public void bind(String category) {
                FridgeIngredientInCategoryAdapter innerAdapter = new FridgeIngredientInCategoryAdapter(innerView.getContext(), Controller.getFridge().getSpecifiedTypeOfIngredient(category));
                innerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
                innerView.setAdapter(innerAdapter);
            }
        }

    }






