package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
                    when(category) {
                        "Artifacts", "Characters", "Domains", "Elements", "Enemies", "Nations", "Weapons" -> {
                            adapter = SecondListAdapter(mutableListOf<String>())
                            getCorrectNamesForCategory(response.body()!!)
                        }
                        else -> {

                        }
                    }
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
            val dataService = RetrofitHelper.getInstance().create(DataService:: class.java)
            when (category) {
                "Artifacts" -> {
                    val artifactDataCall = dataService.getArtifactDataByName(item.toLowerCase())
                    artifactDataCall.enqueue(object : Callback<ArtifactData> {
                        override fun onResponse(
                            call: Call<ArtifactData>,
                            response: Response<ArtifactData>
                        ) {
                            Log.d(TAG, "getCorrectNames onResponse: ${response.body()?.name} : ${adapter.dataSet}")
                            //itemList.add(response.body()?.name!!)
                            adapter.dataSet?.add(response.body()?.name!!)

                        }
                        override fun onFailure(call: Call<ArtifactData>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                }
                "Characters" -> {
                    val characterDataCall = dataService.getCharacterDataByName(item.toLowerCase())
                    characterDataCall.enqueue(object : Callback<CharacterData> {
                        override fun onResponse(
                            call: Call<CharacterData>,
                            response: Response<CharacterData>
                        ) {
                            adapter.dataSet?.add(response.body()?.name!!)
                        }

                        override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                }
                "Domains" -> {
                    val domainDataCall = dataService.getDomainDataByName(item.toLowerCase())
                    domainDataCall.enqueue(object : Callback<DomainData> {
                        override fun onResponse(
                            call: Call<DomainData>,
                            response: Response<DomainData>
                        ) {
                            adapter.dataSet?.add(response.body()?.name!!)
                        }

                        override fun onFailure(call: Call<DomainData>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                }
                "Elements" -> {
                    val elementDataCall = dataService.getElementDataByName(item.toLowerCase())
                    elementDataCall.enqueue(object : Callback<ElementData> {
                        override fun onResponse(
                            call: Call<ElementData>,
                            response: Response<ElementData>
                        ) {
                            adapter.dataSet?.add(response.body()?.name!!)
                        }

                        override fun onFailure(call: Call<ElementData>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                }
                "Enemies" -> {
                    val enemyDataCall = dataService.getEnemyDataByName(item.toLowerCase())
                    enemyDataCall.enqueue(object : Callback<EnemyData> {
                        override fun onResponse(
                            call: Call<EnemyData>,
                            response: Response<EnemyData>
                        ) {
                            adapter.dataSet?.add(response.body()?.name!!)
                        }
                        override fun onFailure(call: Call<EnemyData>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                }
                "Nations" -> {
                    val nationDataCall = dataService.getNationDataByName(item.toLowerCase())
                    nationDataCall.enqueue(object : Callback<NationData> {
                        override fun onResponse(
                            call: Call<NationData>,
                            response: Response<NationData>
                        ) {
                            adapter.dataSet?.add(response.body()?.name!!)
                        }

                        override fun onFailure(call: Call<NationData>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                }
                "Weapons" -> {
                    val weaponDataCall = dataService.getWeaponDataByName(item.toLowerCase())
                }
                else -> {

                }
                //add item for each type                            }
            }
            adapter.dataSet?.sort()
            adapter.notifyDataSetChanged()
        }
        //Log.d(TAG, "getCorrectNamesForCategory $itemList")
        //itemList.remove("")
//        var immutableList: List<String> = itemList
        //immutableList = immutableList.sortedBy { it }
        //return itemList
    }

}