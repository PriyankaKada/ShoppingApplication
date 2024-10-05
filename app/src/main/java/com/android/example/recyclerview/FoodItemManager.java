package com.android.example.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class FoodItemManager {

    private static FoodItemManager instance;
    private List<FoodItem> favoriteCities;
    private List<FoodItem> cartCities;

    private FoodItemManager() {
        favoriteCities = new ArrayList<>();
        cartCities = new ArrayList<>();
    }

    public static FoodItemManager getInstance() {
        if (instance == null) {
            instance = new FoodItemManager();
        }
        return instance;
    }

    public void addToFavorites(FoodItem foodItem) {
        if (!favoriteCities.contains(foodItem)) {
            favoriteCities.add(foodItem);
        }
    }

    public void removeFromFavorites(FoodItem foodItem) {
        favoriteCities.remove(foodItem);
    }

    public List<FoodItem> getFavoriteCities() {
        return favoriteCities;
    }

    public void addToCart(FoodItem foodItem) {
//        if (!cartCities.contains(FoodItem)) {
            cartCities.add(foodItem);
//        }
    }
    public void removeAllFromCart() {
        cartCities.clear();
    }

    public void removeFromCart(FoodItem foodItem) {
        cartCities.remove(foodItem);
    }

    public List<FoodItem> getCartCities() {
        return cartCities;
    }
}
