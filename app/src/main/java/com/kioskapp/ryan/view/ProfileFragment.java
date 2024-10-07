package com.kioskapp.ryan.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.example.recyclerview.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/**This fragment is used for Displaying FavoritesFood,CartFunctionality and User Location
 * */

public class ProfileFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "ProfileFragment";
    private NavController navController;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private MapView mapView;
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize NavController
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        // Find buttons
        Button buttonFavorites = view.findViewById(R.id.buttonFavorites);
        Button buttonCart = view.findViewById(R.id.buttonCart);
        Button buttonGetLocation = view.findViewById(R.id.buttonGetLocation);

        // Initialize MapView
        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Set click listeners
        buttonFavorites.setOnClickListener(v -> navController.navigate(R.id.action_profileFragment_to_favoritesFragment));
        buttonCart.setOnClickListener(v -> navController.navigate(R.id.action_profileFragment_to_cartFragment));
        buttonGetLocation.setOnClickListener(v -> requestCurrentLocation());
    }
    /**This method is used to request location from the user
     * */

    private void requestCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // Update interval in milliseconds
        locationRequest.setFastestInterval(5000); // Fastest update interval

        fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null && locationResult.getLastLocation() != null) {
                    updateMapLocation(locationResult.getLastLocation());
                    fusedLocationClient.removeLocationUpdates(this); // Stop updates after getting location
                } else {
                    Toast.makeText(getContext(), "Location not available", Toast.LENGTH_SHORT).show();
                }
            }
        }, null);
    }
    /**This method is used to update user's location on the map
     * */
    private void updateMapLocation(Location location) {
        if (googleMap != null) {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f));
            googleMap.addMarker(new MarkerOptions().position(userLocation).title("You are here"));
            Log.d(TAG, "User location updated: " + userLocation);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.googleMap = map;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    /**This method is used to handle user's action after permission is handled by user
     * */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestCurrentLocation();
            } else {
                Toast.makeText(getContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
