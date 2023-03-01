package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.publicapiproject.databinding.ActivityConsumablesFoodDetailBinding

class ConsumablesFoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsumablesFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumablesFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}