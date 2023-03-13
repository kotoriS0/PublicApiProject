package com.example.publicapiproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.textViewDomainDetailRewardsLabel.setOnClickListener {

            binding.textViewDomainDetailMonThurs.setOnClickListener {
                changeRecyclerView("Mon/Thurs")
                binding.textViewDomainDetailMonThurs.setTextColor(Color.BLACK)
                binding.textViewDomainDetailTuesFri.setTextColor(Color.GRAY)
                binding.textViewDomainDetailWedSat.setTextColor(Color.GRAY)
                binding.textViewDomainDetailSun.setTextColor(Color.GRAY)
            }
            binding.textViewDomainDetailTuesFri.setOnClickListener {
                changeRecyclerView("Tues/Fri")
                binding.textViewDomainDetailMonThurs.setTextColor(Color.GRAY)
                binding.textViewDomainDetailTuesFri.setTextColor(Color.BLACK)
                binding.textViewDomainDetailWedSat.setTextColor(Color.GRAY)
                binding.textViewDomainDetailSun.setTextColor(Color.GRAY)
            }
            binding.textViewDomainDetailWedSat.setOnClickListener {
                changeRecyclerView("Wed/Sat")
                binding.textViewDomainDetailMonThurs.setTextColor(Color.GRAY)
                binding.textViewDomainDetailTuesFri.setTextColor(Color.GRAY)
                binding.textViewDomainDetailWedSat.setTextColor(Color.BLACK)
                binding.textViewDomainDetailSun.setTextColor(Color.GRAY)
            }
            binding.textViewDomainDetailSun.setOnClickListener {
                changeRecyclerView("Sun")
                binding.textViewDomainDetailMonThurs.setTextColor(Color.GRAY)
                binding.textViewDomainDetailTuesFri.setTextColor(Color.GRAY)
                binding.textViewDomainDetailWedSat.setTextColor(Color.GRAY)
                binding.textViewDomainDetailSun.setTextColor(Color.BLACK)
            }
        }

        binding.textViewDomainDetailRequirementsLabel.setOnClickListener {
            changeRecyclerView("none")
            binding.textViewDomainDetailRequirementsLabel.setTextColor(Color.BLACK)
            binding.textViewDomainDetailRewardsLabel.setTextColor(Color.GRAY)

            binding.groupDomainDetailDays.visibility = View.GONE
        }
        binding.textViewDomainDetailRewardsLabel.setOnClickListener {
            changeRecyclerView("Sun")
            binding.textViewDomainDetailMonThurs.setTextColor(Color.GRAY)
            binding.textViewDomainDetailTuesFri.setTextColor(Color.GRAY)
            binding.textViewDomainDetailWedSat.setTextColor(Color.GRAY)
            binding.textViewDomainDetailSun.setTextColor(Color.BLACK)

            binding.textViewDomainDetailRequirementsLabel.setTextColor(Color.GRAY)
            binding.textViewDomainDetailRewardsLabel.setTextColor(Color.BLACK)

            binding.groupDomainDetailDays.visibility = View.VISIBLE
        }
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

                changeRecyclerView("none")
                binding.textViewDomainDetailRequirementsLabel.setTextColor(Color.BLACK)
                binding.textViewDomainDetailRewardsLabel.setTextColor(Color.GRAY)

                binding.groupDomainDetailDays.visibility = View.GONE
            }

            override fun onFailure(call: Call<DomainData>, t: Throwable) {
            }
        })
    }

    fun changeRecyclerView(day: String) {
        if(day == "none") {
            adapter = DomainDetailAdapter(item, "Requirements", "none")
        }
        else {
            adapter = DomainDetailAdapter(item, "Rewards", day)
        }
        binding.recyclerViewDomainDetail.adapter = adapter
        binding.recyclerViewDomainDetail.layoutManager = LinearLayoutManager(this@DomainDetailActivity)
    }
}