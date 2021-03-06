package com.example.myplaces.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaces.views.PlaceItemViewHolder
import com.example.myplaces.viewmodels.PlaceItem
import java.lang.Integer.min

class PlacesListAdapter(private val list: List<PlaceItem>) : RecyclerView.Adapter<PlaceItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return PlaceItemViewHolder(inflater,parent)

    }

    override fun getItemCount(): Int {
        return min(list.size,10)
    }

    override fun onBindViewHolder(holder: PlaceItemViewHolder, position: Int) {

        holder.bind(list[position])
    }
}