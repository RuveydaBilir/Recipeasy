package com.example.recipeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;

import java.util.ArrayList;

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
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView categoryName;
        private TextView categoryNum;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageView);
            categoryName = itemView.findViewById(R.id.textView3);
            categoryNum = itemView.findViewById(R.id.ingredient_group_count);

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





