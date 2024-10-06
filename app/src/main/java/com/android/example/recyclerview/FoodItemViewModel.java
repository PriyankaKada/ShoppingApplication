package com.android.example.recyclerview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodItemViewModel extends ViewModel {
    private final MutableLiveData<List<FoodItem>> foodItemsLiveData;
    private final List<FoodItem> foodItemsFull;

    public FoodItemViewModel() {
        foodItemsLiveData = new MutableLiveData<>();
        foodItemsFull = FoodItemManager.getInstance().loadData(); // Assuming this returns all food items
        foodItemsLiveData.setValue(new ArrayList<>(foodItemsFull));
    }

    public LiveData<List<FoodItem>> getFoodItems() {
        return foodItemsLiveData;
    }

    public void filter(String text) {
        if (text.isEmpty()) {
            foodItemsLiveData.setValue(new ArrayList<>(foodItemsFull));
        } else {
            String filterPattern = text.toLowerCase().trim();
            List<FoodItem> filteredList = new ArrayList<>();
            for (FoodItem item : foodItemsFull) {
                if (item.getName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(item);
                }
            }
            foodItemsLiveData.setValue(filteredList);
        }
    }
}
