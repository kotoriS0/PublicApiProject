package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.publicapiproject.databinding.ActivitySecondListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondListActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SecondListActivity"
    }
    private lateinit var binding: ActivitySecondListBinding
    lateinit var adapter: SecondListAdapter

    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        category = intent.getStringExtra(MainCategoryAdapter.EXTRA_CATEGORY) ?: ""

        getNamesApiCall()
    }

    private fun getNamesApiCall() {
        val dataService = RetrofitHelper.getInstance().create(DataService:: class.java)
        val namesDataCall = dataService.getListOfNamesByType(category)

        namesDataCall.enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                if(response.body() != null) {
                    var itemList : MutableList<String>
                    for(item in response.body()!!) {
                        when(category) {
                            "artifact" -> {
                                //add item for each type                            }
                        }
                    }
                    adapter = SecondListAdapter(response.body())
                }
                binding.recyclerViewSecondList.adapter = adapter
                binding.recyclerViewSecondList.layoutManager = LinearLayoutManager(this@SecondListActivity)
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

}