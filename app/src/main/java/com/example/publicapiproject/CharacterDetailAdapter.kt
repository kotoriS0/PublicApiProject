package com.example.publicapiproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CharacterDetailAdapter(var character: CharacterData, var selected: String) : RecyclerView.Adapter<CharacterDetailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewItemName: TextView
        var textViewItemUnlock: TextView
        var textViewItemDescription: TextView
        val layout: ConstraintLayout

        init {
            textViewItemName = view.findViewById(R.id.textView_itemCharacterDetail_name)
            textViewItemUnlock = view.findViewById(R.id.textView_itemCharacterDetail_unlock)
            textViewItemDescription = view.findViewById(R.id.textView_itemCharacterDetail_description)
            layout = view.findViewById(R.id.layout_characterDetail)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_character_detail, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var name = ""
        var unlock = ""
        var description = ""
        when(selected) {
            "Skill Talents" -> {
                val dataSet = character.skillTalents?.get(position)
                name = dataSet?.name!!
                unlock = dataSet.unlock!!
                description = dataSet.description!!
            }
            "Passive Talents" -> {
                val dataSet = character.passiveTalents?.get(position)
                name = dataSet?.name!!
                unlock = dataSet.unlock!!
                description = dataSet.description!!
            }
            "Constellations" -> {
                val dataSet = character.constellations?.get(position)
                name = dataSet?.name!!
                unlock = dataSet.unlock!!
                description = dataSet.description!!
            }
        }
        viewHolder.textViewItemName.text = name
        viewHolder.textViewItemUnlock.text = unlock
        viewHolder.textViewItemDescription.text = description
    }

    override fun getItemCount(): Int {
        return when (selected) {
            "Skill Talents" -> character.skillTalents?.size ?: 0
            "Passive Talents" -> character.passiveTalents?.size ?: 0
            "Constellations" -> character.constellations?.size ?: 0
            else -> 0
        }
    }
}