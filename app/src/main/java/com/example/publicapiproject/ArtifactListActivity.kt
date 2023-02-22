package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.publicapiproject.databinding.ActivityArtifactListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtifactListActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ArtifactListActivity"
    }

    private lateinit var binding: ActivityArtifactListBinding
    //lateinit var adapter:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtifactListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun getArtifactNamesApiCall() {
        val dataService = RetrofitHelper.getInstance().create(DataService:: class.java)
        val artifactNamesDataCall = dataService.getArtifactNames()

        artifactNamesDataCall.enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
}