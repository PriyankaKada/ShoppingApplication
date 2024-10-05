package com.android.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<City> favoriteCities;

    public FavoritesAdapter(List<City> favoriteCities) {
        this.favoriteCities = favoriteCities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favourite_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = favoriteCities.get(position);
        holder.bind(city);
    }

    @Override
    public int getItemCount() {
        return favoriteCities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewCity;
        private TextView textViewCityName;
        private TextView textViewCityInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCity = itemView.findViewById(R.id.imageViewCity);
            textViewCityName = itemView.findViewById(R.id.textViewCityName);
            textViewCityInfo = itemView.findViewById(R.id.textViewCityInfo);
        }

        public void bind(City city) {
            textViewCityName.setText(city.getName());
            textViewCityInfo.setText(city.getDescription());
             imageViewCity.setImageResource(city.getListImage());
        }
    }
}
