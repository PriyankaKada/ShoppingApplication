package com.android.example.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ProfileFragment extends Fragment {

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState); // Corrected line

        // Initialize NavController
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        // Find buttons
        Button buttonFavorites = view.findViewById(R.id.buttonFavorites);
        Button buttonCart = view.findViewById(R.id.buttonCart);

        // Set click listeners
        buttonFavorites.setOnClickListener(v -> navController.navigate(R.id.action_profileFragment_to_favoritesFragment));
        buttonCart.setOnClickListener(v -> navController.navigate(R.id.action_profileFragment_to_cartFragment));
    }
}
