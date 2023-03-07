package com.example.publicapiproject

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DomainDetailAdapter(var domainData: DomainData, var selected: String) : RecyclerView.Adapter<DomainDetailAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}