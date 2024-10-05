package com.android.example.recyclerview;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.example.recyclerview.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private FoodItemModel FoodItemModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FoodItemModel = new ViewModelProvider(requireActivity()).get(FoodItemModel.class);

        Integer position = getArguments() != null ? getArguments().getInt("position") : null;
        FoodItem foodItem;

        if (position != null) {
             foodItem = FoodItemModel.getFoodItems().get(position);
            binding.imageDetail.setImageResource(foodItem.getDetailImage());
            binding.nameDetail.setText(foodItem.getName());
            binding.description.setText(foodItem.getDescription());
        } else {
            foodItem = null;
        }

        // Set up button click listeners
        binding.buttonAddToCart.setOnClickListener(v -> {
            FoodItemManager.getInstance().addToCart(foodItem);
            // Optionally show a message to the user
        });

        binding.buttonAddToFavorite.setOnClickListener(v -> {
            FoodItemManager.getInstance().addToFavorites(foodItem);
            // Optionally show a message to the user
        });
    }

    private void addToCart(FoodItem foodItem) {
        // Implement your logic to add the FoodItem to the cart
    }

    private void addToFavorite(FoodItem foodItem) {
        // Implement your logic to add the FoodItem to favorites
    }
}
