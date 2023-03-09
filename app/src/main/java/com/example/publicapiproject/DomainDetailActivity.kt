package com.example.publicapiproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.publicapiproject.SecondListAdapter.Companion.EXTRA_ITEM
import com.example.publicapiproject.databinding.ActivityDomainDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DomainDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDomainDetailBinding
    lateinit var adapter: DomainDetailAdapter

    lateinit var item: DomainData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDomainDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ITEM)
        getFoodDataByIdApiCall(id!!)
    }

    private fun getFoodDataByIdApiCall(id: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService::class.java)
        val domainDataCall = dataService.getDomainDataByName(id)

        domainDataCall.enqueue(object : Callback<DomainData> {
            override fun onResponse(call: Call<DomainData>, response: Response<DomainData>) {
                item = response.body()!!
                binding.textViewDomainDetailName.text = item.name
                binding.textViewDomainDetailType.text = item.type
                binding.textViewDomainDetailDescription.text = item.description
                binding.textViewDomainDetailLocation.text = item.location
                binding.textViewDomainDetailRecommendedElements.text = item.recommendedElements.toString()

                changeRecyclerView("Requirements", "none")
                binding.textViewDomainDetailRequirementsLabel.setTextColor(Color.BLACK)
            }

            override fun onFailure(call: Call<DomainData>, t: Throwable) {
            }
        })
    }

    fun changeRecyclerView(selected: String, day: String) {
        if(day == "none") {
            adapter = DomainDetailAdapter(item, )
        }
    }
}