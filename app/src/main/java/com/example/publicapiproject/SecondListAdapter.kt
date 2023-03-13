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

class SecondListAdapter(var dataSet: MutableList<String>?, var category: String, var idDataSet: List<String>) : RecyclerView.Adapter<SecondListAdapter.ViewHolder>() {
    companion object {
        val EXTRA_ITEM = "item"
        val TAG = "SecondListAdapter"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewItem: TextView
        val layout: ConstraintLayout

        init {
            textViewItem = view.findViewById(R.id.textView_itemSecond_item)
            layout = view.findViewById(R.id.layout_secondList)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_second_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet?.get(position)?.capitalize()
        val id = item?.toLowerCase()?.replace("(", "")?.replace(")", "")?.replace(" ", "-")
        viewHolder.textViewItem.text = item
        viewHolder.layout.setOnClickListener {
            Toast.makeText(it.context, item, Toast.LENGTH_SHORT).show()
            when(category) {
                "Artifacts" -> {
                    val detailIntent = Intent(it.context, ArtifactDetailActivity::class.java)
                    detailIntent.putExtra(EXTRA_ITEM, idDataSet[position])
                    it.context.startActivity(detailIntent)
                }
                "Boss" -> {

                }
                "Characters" -> {
                    val detailIntent = Intent(it.context, CharacterDetailActivity::class.java)
                    detailIntent.putExtra(EXTRA_ITEM, id)
                    it.context.startActivity(detailIntent)
                }
                "Consumables" -> {
                    val detailIntent = Intent(it.context, ConsumablesListActivity::class.java)
                    detailIntent.putExtra(EXTRA_ITEM, item?.toLowerCase())
                    it.context.startActivity(detailIntent)
                }
                "Domains" -> {
                    val detailIntent = Intent(it.context, DomainDetailActivity::class.java)
                    detailIntent.putExtra(EXTRA_ITEM, id)
                    it.context.startActivity(detailIntent)
                }
                "Elements" -> {
                    val detailIntent = Intent(it.context, ElementDetailActivity::class.java)
                    detailIntent.putExtra(EXTRA_ITEM, item?.toLowerCase())
                    it.context.startActivity(detailIntent)
                }
                "Enemies" -> {

                }
                "Materials" -> {

                }
                "Nations" -> {

                }
                "Weapons" -> {

                }
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0
}