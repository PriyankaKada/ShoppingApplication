package com.kioskapp.ryan;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.example.recyclerview.R;
import com.android.example.recyclerview.databinding.FragmentMainBinding;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private FoodItemViewModel foodItemViewModel;
    private FoodItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodItemViewModel = new ViewModelProvider(requireActivity()).get(FoodItemViewModel.class);

        int numberOfColumns = getResources().getInteger(R.integer.nbcol);
        adapter = new FoodItemAdapter(requireActivity(), new ArrayList<>());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), numberOfColumns));
        binding.recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecor = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(itemDecor);

        // Observe the LiveData
        foodItemViewModel.getFoodItems().observe(getViewLifecycleOwner(), foodItems -> {
            adapter.updateFoodItems(foodItems);
        });

        // Set up the search functionality
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                foodItemViewModel.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

}