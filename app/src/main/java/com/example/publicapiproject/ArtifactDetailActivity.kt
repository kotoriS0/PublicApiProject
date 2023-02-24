package com.example.publicapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.publicapiproject.SecondListAdapter.Companion.EXTRA_ITEM
import com.example.publicapiproject.databinding.ActivityArtifactDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtifactDetailActivity : AppCompatActivity() {
    companion object {
        val TAG = "ArtifactDetailActivity"
    }

    private lateinit var binding: ActivityArtifactDetailBinding

    private lateinit var artifact: ArtifactData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtifactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ITEM)
        Log.d(TAG, "$id")
        getArtifactDataByNameApiCall(id!!)
    }

    fun getArtifactDataByNameApiCall(name: String) {
        val dataService = RetrofitHelper.getInstance().create(DataService:: class.java)
        val artifactDataCall = dataService.getArtifactDataByName(name)

        artifactDataCall.enqueue(object : Callback<ArtifactData> {
            override fun onResponse(
                call: Call<ArtifactData>,
                response: Response<ArtifactData>
            ) {
                //Log.d(TAG, "onResponse: ${response.body()}")
                if(response.body() != null) {
                    artifact = response.body()!!
                    binding.textViewArtifactDetailName.text = artifact.name
                    binding.textViewArtifactDetailMaxRarity.text = artifact.maxRarity.toString()
                    binding.textViewArtifactDetailTwoPieceBonus.text = artifact.twoPieceBonus
                    binding.textViewArtifactDetailFourPieceBonus.text = artifact.fourPieceBonus
                }
            }

            override fun onFailure(call: Call<ArtifactData>, t: Throwable) {
                Log.d(MainActivity.TAG, "onFailure: ${t.message}")
            }
        })
    }
}