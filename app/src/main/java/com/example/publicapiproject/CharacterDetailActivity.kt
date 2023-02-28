package com.example.publicapiproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicapiproject.SecondListAdapter.Companion.EXTRA_ITEM
import com.example.publicapiproject.databinding.ActivityCharacterDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailActivity : AppCompatActivity() {
    companion object {
        val TAG = "CharacterDetailActivity"
    }

    private lateinit var binding: ActivityCharacterDetailBinding
    lateinit var adapter: CharacterDetailAdapter

    private lateinit var character: CharacterData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ITEM)
        getCharacterDataByNameApiCall(id!!)

        binding.textViewCharacterDetailSkillTalentsLabel.setOnClickListener {
            changeRecyclerView("Skill Talents")
            binding.textViewCharacterDetailSkillTalentsLabel.setTextColor(Color.BLACK)
            binding.textViewCharacterDetailPassiveTalentsLabel.setTextColor(Color.GRAY)
            binding.textViewCharacterDetailConstellationsLabel.setTextColor(Color.GRAY)
        }
        binding.textViewCharacterDetailPassiveTalentsLabel.setOnClickListener {
            changeRecyclerView("Passive Talents")
            binding.textViewCharacterDetailSkillTalentsLabel.setTextColor(Color.GRAY)
            binding.textViewCharacterDetailPassiveTalentsLabel.setTextColor(Color.BLACK)
            binding.textViewCharacterDetailConstellationsLabel.setTextColor(Color.GRAY)
        }
        binding.textViewCharacterDetailConstellationsLabel.setOnClickListener {
            changeRecyclerView("Constellations")
            binding.textViewCharacterDetailSkillTalentsLabel.setTextColor(Color.GRAY)
            binding.textViewCharacterDetailPassiveTalentsLabel.setTextColor(Color.GRAY)
            binding.textViewCharacterDetailConstellationsLabel.setTextColor(Color.BLACK)
        }
    }

    fun getCharacterDataByNameApiCall(name: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService::class.java)
        val characterDataCall = dataService.getCharacterDataByName(name)

        characterDataCall.enqueue(object: Callback<CharacterData> {
            override fun onResponse(
                call: Call<CharacterData>,
                response: Response<CharacterData>
            ) {
                //Log.d(TAG,"onResponse: ${response.body()}")
                character = response.body()!!
                binding.textViewCharacterDetailName.text = character.name
                binding.textViewCharacterDetailTitle.text = character.title
                binding.textViewCharacterDetailDescription.text = character.description
                binding.textViewCharacterDetailVision.text = character.vision
                binding.textViewCharacterDetailWeapon.text = character.weapon
                binding.textViewCharacterDetailGender.text = character.gender
                binding.textViewCharacterDetailNation.text = character.nation
                binding.textViewCharacterDetailAffiliation.text = character.affiliation
                binding.textViewCharacterDetailSpecialDish.text = character.specialDish
                binding.textViewCharacterDetailRarity.text = character.rarity.toString()
                binding.textViewCharacterDetailBirthday.text = character.birthday

                if(response.body() != null) {
                    adapter = CharacterDetailAdapter(character, "SkillTalents")
                }
                binding.recyclerViewCharacterDetail.adapter = adapter
                binding.recyclerViewCharacterDetail.layoutManager = LinearLayoutManager(this@CharacterDetailActivity)
            }

            override fun onFailure(call: Call<CharacterData>, t: Throwable) {
            }
        })
    }

    fun changeRecyclerView(selected: String) {
        adapter = CharacterDetailAdapter(character, selected)
        binding.recyclerViewCharacterDetail.adapter = adapter
        binding.recyclerViewCharacterDetail.layoutManager = LinearLayoutManager(this@CharacterDetailActivity)
    }
}