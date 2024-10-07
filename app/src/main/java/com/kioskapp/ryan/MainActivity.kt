package com.kioskapp.ryan

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.example.recyclerview.R

/**This is Entry point to the Application
 * Application is architect as Single Activity Application */

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        // Optionally set a title if needed
        if (supportActionBar != null) {
            supportActionBar?.title = "Food Items List"
        }

    }
    /**This Method is used to create Menu Items
     *
     *
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
    /**This method is used to handle click event of Menu Items
     *
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_icon1 -> {
                // Navigate to favoritesFragment
                navController.navigate(R.id.action_mainFragment_to_profileFragment)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }


    /**This method initializes Nav Graph in the Application
     *
     * */
    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

}
