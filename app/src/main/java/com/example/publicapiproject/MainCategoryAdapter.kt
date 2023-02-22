package com.example.publicapiproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MainCategoryAdapter(var dataSet: Map<String, List<String>>?) : RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {

    companion object {
        val EXTRA_CATEGORY = "category"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCategory: TextView
        val layout: ConstraintLayout

        init {
            textViewCategory = view.findViewById(R.id.textView_itemMain_category)
            layout = view.findViewById(R.id.layout_mainItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_main_menu, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val category = dataSet?.get("types")?.get(position)?.capitalize()
        viewHolder.textViewCategory.text = category
        viewHolder.layout.setOnClickListener {
            //Toast.makeText(it.context, category, Toast.LENGTH_SHORT).show()
            when(viewHolder.textViewCategory.text) {
                "Artifacts" -> {
                    val detailIntent = Intent(it.context, SecondListActivity::class.java)
                    detailIntent.putExtra(EXTRA_CATEGORY, category)
                    it.context.startActivity(detailIntent)
                }
                else -> {

                }
            }

        }
    }

    override fun getItemCount() = dataSet?.get("types")?.size ?: 0
}