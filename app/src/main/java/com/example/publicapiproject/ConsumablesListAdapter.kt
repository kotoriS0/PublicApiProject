package com.example.publicapiproject

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ConsumablesListAdapter(var dataSet: MutableList<List<String>>, var category: String) : RecyclerView.Adapter<ConsumablesListAdapter.ViewHolder>() {
    companion object {
        val TAG = "ConsumablesListAdapter"
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
        val item = dataSet[position][0]
        val type = dataSet[position][1]
        Log.d(TAG, "$item / $dataSet")
        viewHolder.textViewName.text = item
        viewHolder.textViewType.text = type
        viewHolder.layout.setOnClickListener {

        }
    }

    override fun getItemCount() = dataSet.size ?: 0
}