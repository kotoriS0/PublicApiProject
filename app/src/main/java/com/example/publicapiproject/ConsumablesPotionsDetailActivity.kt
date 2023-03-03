package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicapiproject.ConsumablesListAdapter.Companion.EXTRA_KEY
import com.example.publicapiproject.databinding.ActivityConsumablesPotionsDetailBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
class ConsumablesPotionsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsumablesPotionsDetailBinding
    lateinit var adapter: ConsumablesPotionsDetailAdapter

    private lateinit var item: ConsumablesPotionsData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumablesPotionsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val key = intent.getStringExtra(EXTRA_KEY)
        getPotionDataByKeyApiCall(key!!)
    }

    fun getPotionDataByKeyApiCall(key: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService::class.java)
        val potionDataCall = dataService.getConsumablesPotions()

        potionDataCall.enqueue(object : Callback<Map<String, ConsumablesPotionsData>> {
            override fun onResponse(
                call: Call<Map<String, ConsumablesPotionsData>>,
                response: Response<Map<String, ConsumablesPotionsData>>
            ) {
                item = response.body()?.get(key)!!
                binding.textViewConsumablesPostionDetailName.text = item.name
                binding.textViewConsumablesPotionDetailEffect.text = item.effect
                binding.textViewConsumablesPotionsDetailRarity.text = item.rarity.toString()
                adapter = ConsumablesPotionsDetailAdapter(item.crafting!!)
                binding.recyclerViewConsumablesPotionDetail.adapter = adapter
                binding.recyclerViewConsumablesPotionDetail.layoutManager = LinearLayoutManager(this@ConsumablesPotionsDetailActivity)

            }

            override fun onFailure(call: Call<Map<String, ConsumablesPotionsData>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}