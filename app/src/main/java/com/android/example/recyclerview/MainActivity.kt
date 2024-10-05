package com.android.example.recyclerview


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var buttonFavorites: Button
    private lateinit var buttonCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        // Set up button references
        buttonFavorites = findViewById(R.id.buttonFavorites)
        buttonCart = findViewById(R.id.buttonCart)

        // Set click listener for Favorites button
        buttonFavorites.setOnClickListener {
//            hideButtons()
            navController.navigate(R.id.action_mainFragment_to_favoritesFragment)
        }

        // Set click listener for Cart button
        buttonCart.setOnClickListener {
//            hideButtons()
            navController.navigate(R.id.action_mainFragment_to_cartFragment)
        }
    }



    private fun showButtons() {
        buttonFavorites.visibility = View.VISIBLE
        buttonCart.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

    override fun onResume() {
        super.onResume()
        // Show buttons only when returning to MainFragment
        if (navController.currentDestination?.id == R.id.mainFragment) {
            showButtons()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Show buttons when going back to MainFragment
        if (navController.currentDestination?.id == R.id.mainFragment) {
            showButtons()
        }
    }
}
