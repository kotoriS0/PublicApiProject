package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicapiproject.databinding.ActivityConsumablesListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsumablesListActivity : AppCompatActivity() {
    companion object {
        val TAG = "ConsumablesListActivity"
    }
    private lateinit var  binding: ActivityConsumablesListBinding
    lateinit var adapter: ConsumablesListAdapter

    private lateinit var category: String
    private var namesList = mutableListOf<List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumablesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        category = intent.getStringExtra(SecondListAdapter.EXTRA_ITEM) ?: ""

        getItemsApiCall()
    }

    private fun getItemsApiCall() {
        val dataService = RetrofitHelper.getInstance().create(DataService::class.java)
        if(category == "food") {
            val foodDataCall = dataService.getConsumablesFood()

            foodDataCall.enqueue(object : Callback<Map<String, ConsumablesFoodData>> {
                override fun onResponse(
                    call: Call<Map<String, ConsumablesFoodData>>,
                    response: Response<Map<String, ConsumablesFoodData>>
                ) {
                    if(response != null) {
                        response.body()?.forEach { item -> namesList.add(listOf(item.value.name!!,
                            item.value.type?.replace(" Dish", "")!!, item.key))}
                    }
                    Log.d(TAG, "food onResponse: $namesList")
                    adapter = ConsumablesListAdapter(namesList, category)
                    binding.recyclerViewConsumablesList.adapter = adapter
                    binding.recyclerViewConsumablesList.layoutManager = LinearLayoutManager(this@ConsumablesListActivity)
                }

                override fun onFailure(call: Call<Map<String, ConsumablesFoodData>>, t: Throwable) {
                    Log.d(TAG, "food onFailure: ${t.message}")
                }
            })
        }
        else {
            val potionsDataCall = dataService.getConsumablesPotions()

            potionsDataCall.enqueue(object : Callback<Map<String, ConsumablesPotionsData>> {
                override fun onResponse(
                    call: Call<Map<String, ConsumablesPotionsData>>,
                    response: Response<Map<String, ConsumablesPotionsData>>
                ) {
                    if(response != null) {
                        response.body()?.forEach { item -> namesList.add(listOf(item.value.name!!, getType(item.value.effect!!), item.key))}
                    }
                    adapter = ConsumablesListAdapter(namesList, category)
                    binding.recyclerViewConsumablesList.adapter = adapter
                    binding.recyclerViewConsumablesList.layoutManager = LinearLayoutManager(this@ConsumablesListActivity)
                }

                override fun onFailure(
                    call: Call<Map<String, ConsumablesPotionsData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    private fun getType(effect: String) : String {
        var type = ""
        type += with(effect) {when{
            contains(" Anemo ") -> "Anemo"
            contains(" Cryo ") -> "Cryo"
            contains(" Dendro ") -> "Dendro"
            contains(" Electro ") -> "Electro"
            contains(" Geo ") -> "Geo"
            contains(" Hydro ") -> "Hydro"
            contains(" Pyro ") -> "Pyro"
            else -> ""
        }}
        type += with(effect) {when{
            contains(" RES ") -> " RES"
            contains(" DMG ") -> " DMG"
            else -> ""
        }}
        return type
    }
}