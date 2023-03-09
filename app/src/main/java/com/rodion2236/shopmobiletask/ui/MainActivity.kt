package com.rodion2236.shopmobiletask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodion2236.shopmobiletask.R
import com.rodion2236.shopmobiletask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}