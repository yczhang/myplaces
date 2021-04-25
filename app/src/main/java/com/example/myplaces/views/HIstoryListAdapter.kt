package com.example.myplaces.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaces.database.SearchResult
import com.example.myplaces.views.PlaceItemViewHolder
import com.example.myplaces.viewmodels.PlaceItem
import java.lang.Integer.min

class HIstoryListAdapter(private val list: List<SearchResult>) : RecyclerView.Adapter<HistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return HistoryItemViewHolder(inflater,parent)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {

        holder.bind(list[position])
    }
}