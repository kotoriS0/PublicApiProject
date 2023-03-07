package com.example.publicapiproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ElementDetailAdapter(var dataSet: List<ElementData.Reaction>) : RecyclerView.Adapter<ElementDetailAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewDescription: TextView
        val layout: ConstraintLayout

        init {
            textViewName = view.findViewById(R.id.textView_itemElementDetail_name)
            textViewDescription = view.findViewById(R.id.textView_itemElementDetail_description)
            layout = view.findViewById(R.id.layout_elementDetailItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_element_detail, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.textViewName.text = item.name
        viewHolder.textViewDescription.text = item.description
    }

    override fun getItemCount() = dataSet.size ?: 0
}