package com.example.recipeasy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.Recipe;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoopingListViewHolder> {
    ArrayList<Ingredient> shoppingList;
    Context context;


    ShoppingListAdapter(Context context, ArrayList<Ingredient> shoppingList){
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.ShoopingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_shopping_list, parent, false);
        return new ShoppingListAdapter.ShoopingListViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ShoopingListViewHolder holder, int position) {
        holder.name.setText(shoppingList.get(position).getName());
        holder.amount.setText(Double.toString(shoppingList.get(position).getAmount()));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    //TODO:
                    //just adds it to the fridge
                    Controller.getFridge().addIngredient(shoppingList.get(position));
                }
                else{
                    holder.itemView.setVisibility(View.GONE);
                }
            }


        });
        setFilteredList(shoppingList);
    }

    public void setFilteredList(ArrayList<Ingredient> filteredList){
        this.shoppingList = filteredList;

        //notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public class ShoopingListViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private CheckBox checkBox;
        private TextView amount;
        public ShoopingListViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView3);
            checkBox = itemView.findViewById(R.id.checkBox);
            amount = itemView.findViewById(R.id.ingredient_count);

        }
    }
}
