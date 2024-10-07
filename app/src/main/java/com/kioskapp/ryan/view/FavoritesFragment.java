package com.kioskapp.ryan.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.recyclerview.R;
import com.kioskapp.ryan.model.FoodItem;
import com.kioskapp.ryan.model.FoodItemManager;

import java.util.List;
/**This fragment is used for Displaying FavoritesFood items selected by the user
 * */
public class FavoritesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        List<FoodItem> favoriteCities = FoodItemManager.getInstance().getFavoriteFoodItems();
        FavoritesAdapter adapter = new FavoritesAdapter(favoriteCities);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
