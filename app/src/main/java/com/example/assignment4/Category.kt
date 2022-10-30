package com.example.assignment4

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class Category : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val ss: String = intent.getStringExtra("username").toString()
        val welcomeText=findViewById<TextView>(R.id.welcomeText)
        welcomeText.text = "Welcome: $ss"

        val beautyLayout=findViewById<LinearLayout>(R.id.beautyLayout)
        beautyLayout.setOnClickListener {
            showToast("Beauty")
        }

        val clothingLayout=findViewById<LinearLayout>(R.id.clothingLayout)
        clothingLayout.setOnClickListener {
            showToast("Clothing")
        }

        val electronicLayout=findViewById<LinearLayout>(R.id.electronicLayout)
        electronicLayout.setOnClickListener {
            showToast("Furniture")
        }

        val foodLayout=findViewById<LinearLayout>(R.id.foodLayout)
        foodLayout.setOnClickListener {
            showToast("Food")
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(
            this,
            "You have chosen the $msg category of shopping",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}