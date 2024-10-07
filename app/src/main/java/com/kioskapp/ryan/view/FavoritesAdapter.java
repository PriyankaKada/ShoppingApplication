package com.kioskapp.ryan.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.recyclerview.R;
import com.kioskapp.ryan.model.FoodItem;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private final List<FoodItem> favoriteFoodItems;

    public FavoritesAdapter(List<FoodItem> favoriteFoodItems) {
        this.favoriteFoodItems = favoriteFoodItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favourite_food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = favoriteFoodItems.get(position);
        holder.bind(foodItem);
    }

    @Override
    public int getItemCount() {
        return favoriteFoodItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewFoodItem;
        private final TextView textViewFoodItemName;
        private final TextView textViewFoodItemInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFoodItem = itemView.findViewById(R.id.imageViewFoodItem);
            textViewFoodItemName = itemView.findViewById(R.id.textViewFoodItemName);
            textViewFoodItemInfo = itemView.findViewById(R.id.textViewFoodItemInfo);
        }

        public void bind(FoodItem foodItem) {
            textViewFoodItemName.setText(foodItem.getName());
            textViewFoodItemInfo.setText(foodItem.getDescription());
             imageViewFoodItem.setImageResource(foodItem.getListImage());
        }
    }
}
