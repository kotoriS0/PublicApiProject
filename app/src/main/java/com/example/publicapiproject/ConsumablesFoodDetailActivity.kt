package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicapiproject.ConsumablesListAdapter.Companion.EXTRA_KEY
import com.example.publicapiproject.databinding.ActivityConsumablesFoodDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsumablesFoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsumablesFoodDetailBinding
    lateinit var adapter: ConsumablesFoodDetailAdapter

    private lateinit var item: ConsumablesFoodData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumablesFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val key = intent.getStringExtra(EXTRA_KEY)
        getFoodDataByKeyApiCall(key!!)
    }

    private fun getFoodDataByKeyApiCall(key: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService::class.java)
        val foodDataCall = dataService.getConsumablesFood()

        foodDataCall.enqueue(object : Callback<Map<String, ConsumablesFoodData>> {
            override fun onResponse(
                call: Call<Map<String, ConsumablesFoodData>>,
                response: Response<Map<String, ConsumablesFoodData>>
            ) {
                item = response.body()?.get(key)!!
                binding.textViewFoodDetailName.text = item.name
                binding.textViewFoodDetailDescription.text = item.description
                binding.textViewFoodDetailType.text = item.type
                binding.textViewFoodDetailEffect.text = item.effect
                binding.textViewFoodDetailRarity.text = item.rarity.toString()
                if(item.hasRecipe!!) {
                    binding.textViewFoodDetailProficiency.text = item.proficiency.toString()
                    adapter = ConsumablesFoodDetailAdapter(item.recipe!!)
                    binding.recyclerViewConsumablesFoodDetail.adapter = adapter
                    binding.recyclerViewConsumablesFoodDetail.layoutManager = LinearLayoutManager(this@ConsumablesFoodDetailActivity)
                }
            }

            override fun onFailure(call: Call<Map<String, ConsumablesFoodData>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}