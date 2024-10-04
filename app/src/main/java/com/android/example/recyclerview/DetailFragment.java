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
    private CityModel cityModel;

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
        cityModel = new ViewModelProvider(requireActivity()).get(CityModel.class);

        Integer position = getArguments() != null ? getArguments().getInt("position") : null;
        if (position != null) {
            City city = cityModel.getCities().get(position);
            binding.imageDetail.setImageResource(city.getDetailImage());
            binding.nameDetail.setText(city.getName());
            binding.numbertouristDetail.setText(city.getTouristNumber() + getString(R.string.touristText));
            binding.description.setText(city.getDescription());
            binding.places.setText(getString(R.string.places) + city.getPlaces());
        }
    }
}
