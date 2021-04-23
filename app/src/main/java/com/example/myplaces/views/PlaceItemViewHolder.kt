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

    init {
    }

    fun bind(item: PlaceItem)
    {

    }

    override fun onClick(p0: View?) {

    }
}