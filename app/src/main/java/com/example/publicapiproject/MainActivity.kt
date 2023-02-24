package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicapiproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: MainCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMainCategoriesApiCall()
    }

    private fun getMainCategoriesApiCall() {
        val dataService = RetrofitHelper.getInstance().create(DataService:: class.java)
        val categoriesDataCall = dataService.getMainCategories()

        categoriesDataCall.enqueue(object: Callback<Map<String, List<String>>> {
            override fun onResponse(
                call: Call<Map<String, List<String>>>,
                response: Response<Map<String, List<String>>>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                if(response.body() != null) {
                    adapter = MainCategoryAdapter(response.body())
                }
                binding.recyclerViewMain.adapter = adapter
                binding.recyclerViewMain.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<Map<String, List<String>>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
}