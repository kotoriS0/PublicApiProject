package com.example.publicapiproject

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DomainDetailAdapter(var domainData: DomainData, var selected: String, var day: String) : RecyclerView.Adapter<DomainDetailAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLevelRequire: TextView
        val textViewRankRequire: TextView
        val textViewRecLevelRequire: TextView
        val textViewLeyLineRequire: TextView
        val recyclerViewRequireDrops: RecyclerView

        val textViewLevelReward: TextView
        val textViewAdventureXpReward: TextView
        val textViewCompanionshipXpReward: TextView
        val textViewMoraReward: TextView

        init {
            textViewLevelRequire = view.findViewById(R.id.textView_domainDetailRequirement_level)
            textViewRankRequire = view.findViewById(R.id.textView_domainDetailRequirement_adventureRank)
            textViewRecLevelRequire = view.findViewById(R.id.textView_domainDetailRequirement_recommendedLevel)
            textViewLeyLineRequire = view.findViewById(R.id.textView_domainDetailRequirement_leyLineDisorder)
            recyclerViewRequireDrops = view.findViewById(R.id.recyclerView_domainDetailReward)
        }
        init {
            textViewLevelReward = view.findViewById(R.id.textView_domainDetailReward_level)
            textViewAdventureXpReward = view.findViewById(R.id.textView_domainDetailReward_adventureXp)
            textViewCompanionshipXpReward = view.findViewById(R.id.textView_domainDetailReward_companionshipXp)
            textViewMoraReward = view.findViewById(R.id.textView_domainDetailReward_mora)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        lateinit var view:View
        when(selected) {
            "Rewards" -> {
                view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_domain_detail_reward, viewGroup, false)
            }
            else -> {
                view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_domain_detail_requirement, viewGroup, false)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when(selected) {
            "Rewards" -> {
                lateinit var item: DomainData.Details
                lateinit var adapter: Any
                when(day) {
                    "Sun" -> {
                        item = domainData.rewards!![6].details[position]
                        adapter = DomainDropAdapter(item.drops!!)
                    }
                    "Tues/Fri" -> {
                        item = domainData.rewards!![1].details[position]
                        adapter = DomainDropAdapter(item.drops!!)
                    }
                    "Wed/Sat" -> {
                        item = domainData.rewards!![2].details[position]
                        adapter = DomainDropAdapter(item.drops!!)
                    }
                    else -> {
                        item = domainData.rewards!![0].details[position]
                        adapter = DomainDropAdapter(item.drops!!)
                    }
                }
                viewHolder.textViewLevelReward.text = item.level.toString()
                viewHolder.textViewAdventureXpReward.text = item.adventureExperience.toString()
                viewHolder.textViewCompanionshipXpReward.text = item.companionshipExperience.toString()
                viewHolder.textViewMoraReward.text = item.mora.toString()

                viewHolder.recyclerViewRequireDrops.adapter = adapter
                viewHolder.recyclerViewRequireDrops.layoutManager = LinearLayoutManager(viewHolder.textViewLevelRequire.context)
            }
            else -> {
                val item = domainData.requirements!![position]
                viewHolder.textViewLevelRequire.text = item.level.toString()
                viewHolder.textViewRankRequire.text = item.adventureRank.toString()
                viewHolder.textViewRecLevelRequire.text = item.recommendedLevel.toString()
                viewHolder.textViewLeyLineRequire.text = item.leyLineDisorder.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        if(selected == "Rewards") {
            when (day) {
                "Sun" -> {
                    count = domainData.rewards!![0].details.size
                }
                "Tues/Fri" -> {
                    count = domainData.rewards!![1].details.size
                }
                "Wed/Sat" -> {
                    count = domainData.rewards!![2].details.size
                }
                else -> {
                    count = domainData.rewards!![0].details.size
                }
            }
        }
        else {
            count = domainData.requirements!!.size
        }
        return count
    }

    class DomainDropAdapter(var data: List<DomainData.Drops>) : RecyclerView.Adapter<DomainDropAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewNameDrop: TextView
            val textViewRarityDrop: TextView
            val textViewMinDrop: TextView
            val textViewMaxDrop: TextView
            val layoutDrop: ConstraintLayout

            init {
                textViewNameDrop = view.findViewById(R.id.textView_domainDetailRewardDrop_name)
                textViewRarityDrop = view.findViewById(R.id.textView_domainDetailRewardDrop_rarity)
                textViewMinDrop = view.findViewById(R.id.textView_domainDetailRewardDrop_min)
                textViewMaxDrop = view.findViewById(R.id.textView_domainDetailRewardDrop_max)
                layoutDrop = view.findViewById(R.id.layout_domainDetailRewardDrop)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DomainDetailAdapter.DomainDropAdapter.ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_domain_detail_reward_drop, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val item = data[position]
            viewHolder.textViewNameDrop.text = item.name
            viewHolder.textViewRarityDrop.text = item.rarity
            viewHolder.textViewMinDrop.text = item.dropMin.toString()
            viewHolder.textViewMaxDrop.text = item.dropMax.toString()
        }

        override fun getItemCount() = data.size ?: 0
    }

}