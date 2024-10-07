package com.kioskapp.ryan.view;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.example.recyclerview.R;
import com.android.example.recyclerview.databinding.FragmentDetailBinding;
import com.kioskapp.ryan.model.FoodItem;
import com.kioskapp.ryan.model.FoodItemManager;
import com.kioskapp.ryan.viewModel.FoodItemViewModel;

/** This is Detail Fragment
 * It shows Functionality such as
 * 1. Add To Cart
 * 2. Add To Favourite
 * 3. Shown Notification when Item is Added to the cart
 * */
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private FoodItemViewModel foodItemModel;
    private static final String CHANNEL_ID = "food_item_notifications";
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestNotificationPermission();
        createNotificationChannel();
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= 33) { // Check for Android 13 and above
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Food Item Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications for food items added to the cart");
            NotificationManager manager = requireActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodItemModel = new ViewModelProvider(requireActivity()).get(FoodItemViewModel.class);

        Integer position = getArguments() != null ? getArguments().getInt("position") : null;
        FoodItem foodItem = (position != null) ? foodItemModel.loadData().get(position) : null;

        if (foodItem != null) {
            binding.imageDetail.setImageResource(foodItem.getListImage());
            binding.nameDetail.setText(foodItem.getName());
            binding.description.setText(foodItem.getDescription());
        }

        binding.buttonAddToCart.setOnClickListener(v -> {
            if (foodItem != null) {
                Toast.makeText(getContext(),foodItem.getName()+ " has been added to your cart",Toast.LENGTH_SHORT).show();
                FoodItemManager.getInstance().addToCart(foodItem);
                sendNotification(foodItem.getName() + " has been added to your cart!");
            }
        });

        binding.buttonAddToFavorite.setOnClickListener(v -> {
            if (foodItem != null) {
                Toast.makeText(getContext(),foodItem.getName()+ " has been added to Favourite!!",Toast.LENGTH_SHORT).show();

                FoodItemManager.getInstance().addToFavorites(foodItem);
                // Optionally show a message to the user
            }
        });
    }

    private void sendNotification(String message) {
        Log.d("DetailFragment", "Sending notification: " + message);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background) // Replace with your actual notification icon
                .setContentTitle("Item Added to Cart")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build()); // Unique notification ID
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("DetailFragment", "Notification permission granted");
            } else {
                Log.d("DetailFragment", "Notification permission denied");
            }
        }
    }
}
