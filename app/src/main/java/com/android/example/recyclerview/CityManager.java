package com.android.example.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class CityManager {

    private static CityManager instance;
    private List<City> favoriteCities;
    private List<City> cartCities;

    private CityManager() {
        favoriteCities = new ArrayList<>();
        cartCities = new ArrayList<>();
    }

    public static CityManager getInstance() {
        if (instance == null) {
            instance = new CityManager();
        }
        return instance;
    }

    public void addToFavorites(City city) {
        if (!favoriteCities.contains(city)) {
            favoriteCities.add(city);
        }
    }

    public void removeFromFavorites(City city) {
        favoriteCities.remove(city);
    }

    public List<City> getFavoriteCities() {
        return favoriteCities;
    }

    public void addToCart(City city) {
//        if (!cartCities.contains(city)) {
            cartCities.add(city);
//        }
    }

    public void removeFromCart(City city) {
        cartCities.remove(city);
    }

    public List<City> getCartCities() {
        return cartCities;
    }
}
