package com.example.publicapiproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ConsumablesFoodDetailAdapter(var dataSet: List<ConsumablesFoodData.Recipe>) : RecyclerView.Adapter<ConsumablesFoodDetailAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewItem: TextView
        val textViewQuantity: TextView
        val layout: ConstraintLayout

        init {
            textViewItem = view.findViewById(R.id.textView_itemConsumablesPotionsDetail_item)
            textViewQuantity = view.findViewById(R.id.textView_itemConsumablesPotionsDetail_quantity)
            layout = view.findViewById(R.id.layout_consumablesPotionsDetailItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_consumables_food_detail, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.textViewItem.text = item.item
        viewHolder.textViewQuantity.text = item.quantity.toString()
    }

    override fun getItemCount() = dataSet.size ?: 0
}