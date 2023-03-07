package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicapiproject.SecondListAdapter.Companion.EXTRA_ITEM

import com.example.publicapiproject.databinding.ActivityElementDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ElementDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityElementDetailBinding
    lateinit var adapter: ElementDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElementDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_ITEM)
        getElementDataByNameApiCall(name!!)
    }

    private fun getElementDataByNameApiCall(name: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService::class.java)
        val elementDataCall = dataService.getElementDataByName(name)

        elementDataCall.enqueue(object : Callback<ElementData> {
            override fun onResponse(
                call: Call<ElementData>,
                response: Response<ElementData>
            ) {
                binding.textViewElementDetailName.text = response.body()?.name
                adapter = ElementDetailAdapter(response.body()?.reactions!!)
                binding.recyclerViewElementDetail.adapter = adapter
                binding.recyclerViewElementDetail.layoutManager = LinearLayoutManager(this@ElementDetailActivity)
            }

            override fun onFailure(call: Call<ElementData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}