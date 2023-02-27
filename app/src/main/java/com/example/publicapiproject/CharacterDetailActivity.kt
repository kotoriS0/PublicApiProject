package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.publicapiproject.databinding.ActivityCharacterDetailBinding

class CharacterDetailActivity : AppCompatActivity() {
    companion object {
        val TAG = "CharacterDetailActivity"
    }

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var character: CharacterData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ITEM)
        getCharacterDataByNameApiCall(id!!)
    }

    fun getCharacterDataByNameApiCall(name: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService::class.java)
        val characterDataCall = dataService.getCharacterDataByName(name)

        characterDataCall.enqueue(object: Callback<CharacterData> {

        })
    }
}