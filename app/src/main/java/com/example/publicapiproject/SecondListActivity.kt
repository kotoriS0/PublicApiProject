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
import java.util.*

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
                Log.d(TAG, "onResponse: ${response.body()} : $category")
                if(response.body() != null) {
                    // add a blank mutable list
                    adapter = SecondListAdapter(getCorrectNamesForCategory(response.body()!!))
                }

                binding.recyclerViewSecondList.adapter = adapter
                binding.recyclerViewSecondList.layoutManager = LinearLayoutManager(this@SecondListActivity)
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun getCorrectNamesForCategory(list : List<String>) {
        //var itemList = mutableListOf("")
        for (item in list) {
            when (category) {
                "Artifacts" -> {
                    val dataService = RetrofitHelper.getInstance().create(DataService:: class.java)
                    val artifactDataCall = dataService.getArtifactDataByName(item.toLowerCase())
                    artifactDataCall.enqueue(object : Callback<ArtifactData> {
                        override fun onResponse(
                            call: Call<ArtifactData>,
                            response: Response<ArtifactData>
                        ) {
                            Log.d(TAG, "getCorrectNames onResponse: ${response.body()?.name} : ${adapter.dataSet}")
                            //itemList.add(response.body()?.name!!)
                            adapter.dataSet?.add(response.body()?.name!!)
                            adapter.dataSet?.sort()
                            adapter.notifyDataSetChanged()

                        }

                        override fun onFailure(call: Call<ArtifactData>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                }
                "Characters" -> {

                }
                else -> {

                }
                //add item for each type                            }
            }
        }
        //Log.d(TAG, "getCorrectNamesForCategory $itemList")
        //itemList.remove("")
//        var immutableList: List<String> = itemList
        //immutableList = immutableList.sortedBy { it }
        //return itemList
    }

}