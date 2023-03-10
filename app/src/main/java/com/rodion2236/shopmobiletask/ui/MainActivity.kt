package com.rodion2236.shopmobiletask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rodion2236.shopmobiletask.R
import com.rodion2236.shopmobiletask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    //навигация (устал писать, взял у Сереги)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding
            .inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        navController = (supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment)
            .navController

        activityMainBinding.navigation.setupWithNavController(navController)

        navController
            .addOnDestinationChangedListener { _, navDestination: NavDestination, _ ->
                if (navDestination.id == R.id.sign_in_fragment || navDestination.id == R.id.login_fragment) {
                    activityMainBinding.navigation.visibility = View.GONE
                } else {
                    activityMainBinding.navigation.visibility = View.VISIBLE
                }

        }
    }
}