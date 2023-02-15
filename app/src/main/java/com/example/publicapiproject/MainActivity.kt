package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.publicapiproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun getArtifactDataByNameApiCall(name: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService:: class.java)
        val artifactDataCall = dataService.getArtifactDataByName(name)

        artifactDataCall.enqueue(object : Callback<ArtifactData> {
            override fun onResponse(
                call: Call<ArtifactData>,
                response: Response<ArtifactData>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<ArtifactData>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
}