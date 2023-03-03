package com.example.publicapiproject

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ConsumablesListAdapter(var dataSet: MutableList<List<String>>, var category: String) : RecyclerView.Adapter<ConsumablesListAdapter.ViewHolder>() {
    companion object {
        val TAG = "ConsumablesListAdapter"
        val EXTRA_KEY = "key"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewType: TextView
        val layout: ConstraintLayout

        init {
            textViewName = view.findViewById(R.id.textView_itemConsumablesList_name)
            textViewType = view.findViewById(R.id.textView_itemConsumablesList_type)
            layout = view.findViewById(R.id.layout_consumableListItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_consumables_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d(TAG, "${dataSet[position]}")
        val item = dataSet[position][0]
        val type = dataSet[position][1]
        val key = dataSet[position][2]
        viewHolder.textViewName.text = item
        viewHolder.textViewType.text = type
        viewHolder.layout.setOnClickListener {
            when(category) {
                "food" -> {
                    val detailIntent = Intent(it.context, ConsumablesFoodDetailActivity::class.java)
                    detailIntent.putExtra(EXTRA_KEY, key)
                    it.context.startActivity(detailIntent)
                }
                "potions" -> {
                    val detailIntent = Intent(it.context, ConsumablesPotionsDetailActivity::class.java)
                    detailIntent.putExtra(EXTRA_KEY, key)
                    it.context.startActivity(detailIntent)
                }
            }
        }
    }

    override fun getItemCount() = dataSet.size ?: 0
}