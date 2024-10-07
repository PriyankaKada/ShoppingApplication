package com.kioskapp.ryan;

import com.android.example.recyclerview.R;
import com.kioskapp.ryan.FoodItem;
import com.kioskapp.ryan.FoodItemManager;

import junit.framework.TestCase;

import java.util.List;

public class FoodItemViewModelTest extends TestCase {
    private List<FoodItem> favoriteFoodItems;
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testAddToCartFunctionality() {
        FoodItem foodItem8 = new FoodItem(R.drawable.beans_salad, "Bean Salad", 60, 1,
                "A protein-rich salad made with various beans (like kidney, chickpeas, and black beans), diced vegetables, and a tangy dressing. It's hearty and great for a filling meal.");

        FoodItemManager.getInstance().addToCart(foodItem8);

        int size = FoodItemManager.getInstance().getCartFoodItems().size();

        assertEquals(size,1);
    }


    public void testAddToFavouriteFunctionality() {
        FoodItem foodItem8 = new FoodItem(R.drawable.beans_salad, "Bean Salad", 60, 1,
                "A protein-rich salad made with various beans (like kidney, chickpeas, and black beans), diced vegetables, and a tangy dressing. It's hearty and great for a filling meal.");
        FoodItemManager.getInstance().addToFavorites(foodItem8);
        int size=FoodItemManager.getInstance().getFavoriteFoodItems().size();
        assertEquals(size,1);
    }
    public void testRemoveFromFavouriteFunctionality() throws Exception {
        FoodItem foodItem8 = new FoodItem(R.drawable.beans_salad, "Bean Salad", 60, 1,
                "A protein-rich salad made with various beans (like kidney, chickpeas, and black beans), diced vegetables, and a tangy dressing. It's hearty and great for a filling meal.");
        FoodItemManager.getInstance().addToFavorites(foodItem8);
        FoodItemManager.getInstance().removeFromFavorites(foodItem8);
        int size=FoodItemManager.getInstance().getFavoriteFoodItems().size();
        assertEquals(size,0);
    }
}