package com.android.example.recyclerview;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.example.recyclerview.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private CityModel cityModel;

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
        cityModel = new ViewModelProvider(requireActivity()).get(CityModel.class);
        
        int numberOfColumns = getResources().getInteger(R.integer.nbcol);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), numberOfColumns));
        binding.recyclerView.setAdapter(new CityAdapter(requireActivity(), cityModel.getCities()));
        
        DividerItemDecoration itemDecor = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(itemDecor);
    }
}
