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
        if (!cartCities.contains(foodItem)) {
            cartCities.add(foodItem);
        }else {
            cartCities.remove(foodItem);
            cartCities.add(
                    new FoodItem(foodItem.getListImage(),
                            foodItem.getName(),
                            foodItem.getPrice(),
                            foodItem.getQuantity()+1,
                            foodItem.getDescription()));
        }
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
    public List<FoodItem> loadData() {
        FoodItem foodItem1 = new FoodItem(R.drawable.caesar_salad, "Caesar Salad", 30, 1,
                "Crisp romaine lettuce, crunchy croutons, and Parmesan cheese, all tossed in a creamy Caesar dressing made with anchovies, garlic, and lemon juice. Visual Cue: Look for a bowl of bright green romaine, golden croutons, and shavings of cheese.");

        FoodItem foodItem2 = new FoodItem(R.drawable.greek_salad, "Greek Salad", 70, 1,
                "A refreshing mix of ripe tomatoes, cucumbers, red onion, bell peppers, Kalamata olives, and feta cheese, drizzled with olive oil and oregano. Visual Cue: Colorful chunks of vegetables topped with white feta and dark olives.");

        FoodItem foodItem3 = new FoodItem(R.drawable.caprese_salad, "Caprese Salad", 30, 1,
                "Slices of fresh mozzarella and tomatoes, layered with basil leaves and drizzled with balsamic reduction and olive oil. Simple yet delicious. Visual Cue: Alternating red and white slices, garnished with vibrant green basil.");

        FoodItem foodItem4 = new FoodItem(R.drawable.cobb_salad, "Cobb Salad", 50, 1,
                "A hearty salad featuring chopped greens, grilled chicken, bacon, hard-boiled eggs, avocado, tomatoes, and blue cheese, often served with a vinaigrette. Visual Cue: A colorful plate with neatly arranged sections of each ingredient.");

        FoodItem foodItem5 = new FoodItem(R.drawable.nicoise_salad, "Nicoise Salad", 60, 1,
                "A French salad combining tuna (canned or fresh), hard-boiled eggs, green beans, potatoes, olives, and tomatoes, usually dressed with a vinaigrette. Visual Cue: A beautiful arrangement of ingredients, often served on a platter.");

        FoodItem foodItem6 = new FoodItem(R.drawable.quinoa_salad, "Quinoa Salad", 60, 1,
                "A protein-packed salad made with cooked quinoa, mixed vegetables (like bell peppers and cucumbers), herbs, and a light lemon dressing. Visual Cue: Fluffy quinoa mixed with colorful diced veggies and fresh herbs.");

        FoodItem foodItem7 = new FoodItem(R.drawable.pasta_salad, "Pasta Salad", 60, 1,
                "Typically made with cooked pasta, olives, cherry tomatoes, mozzarella, and a vinaigrette. Perfect for picnics and potlucks. Visual Cue: Twirls of pasta mixed with vibrant vegetables and chunks of cheese.");

        FoodItem foodItem8 = new FoodItem(R.drawable.beans_salad, "Bean Salad", 60, 1,
                "A protein-rich salad made with various beans (like kidney, chickpeas, and black beans), diced vegetables, and a tangy dressing. It's hearty and great for a filling meal.");

        List<FoodItem> list = new ArrayList<>();
        list.add(foodItem1);
        list.add(foodItem2);
        list.add(foodItem3);
        list.add(foodItem4);
        list.add(foodItem5);
        list.add(foodItem6);
        list.add(foodItem7);
        list.add(foodItem8);

        return list;
    }
}
