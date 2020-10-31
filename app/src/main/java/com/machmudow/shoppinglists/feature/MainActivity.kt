package com.machmudow.shoppinglists.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}