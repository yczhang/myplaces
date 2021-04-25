package com.example.myplaces.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaces.BuildConfig
import com.example.myplaces.R
import com.example.myplaces.database.SearchResult
import com.example.myplaces.fragments.MainFragment
import com.example.myplaces.viewmodels.MainViewModel
import com.example.myplaces.viewmodels.PlaceItem
import com.squareup.picasso.Picasso

class HistoryItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_history, parent, false)),
    View.OnClickListener {

    private var title: TextView? = null
    private var timeStamp: TextView? = null

    init {
        title = itemView.findViewById(R.id.tv_history_title)
        timeStamp = itemView.findViewById(R.id.tv_history_timestamp)
        itemView.setOnClickListener(this)
    }

    fun bind(item: SearchResult)
    {
        title?.text = item.keyword
        timeStamp?.text = item.timestamp
    }

    override fun onClick(p0: View?) {
        val parentFragment = FragmentManager.findFragment<MainFragment>(itemView)
        val viewModel = ViewModelProvider(parentFragment).get(MainViewModel::class.java)

        viewModel.onHistoryListClick(adapterPosition)
    }
}