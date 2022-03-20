package com.devhuba.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devhuba.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        //Usage;val text = binding.textId
        //val text = binding.textId


    }
}