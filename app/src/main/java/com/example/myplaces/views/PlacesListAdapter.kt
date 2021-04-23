package com.example.movielist.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaces.viewmodels.PlaceItem

class PlacesListAdapter(private val list: List<PlaceItem>) : RecyclerView.Adapter<PlaceItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return PlaceItemViewHolder(inflater,parent)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlaceItemViewHolder, position: Int) {

        holder.bind(list[position])
    }
}