package com.example.myplaces.views

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaces.R
import com.example.myplaces.viewmodels.PlaceItem
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class PlaceItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_place, parent, false)),
    View.OnClickListener {

    private var title: TextView? = null
    private var icon: ImageView? = null
    private var rated: TextView? = null

    init {
        title = itemView.findViewById(R.id.tv_title)
        icon = itemView.findViewById(R.id.iv_icon)
        rated = itemView.findViewById(R.id.tv_rated)
    }

    fun bind(item: PlaceItem)
    {
        title?.text = item.name
        rated?.text = "Rating : ${item.rating}"
    }

    override fun onClick(p0: View?) {
        // TBD
    }
}