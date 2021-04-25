package com.example.myplaces.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaces.BuildConfig
import com.example.myplaces.R
import com.example.myplaces.viewmodels.PlaceItem
import com.squareup.picasso.Picasso

class PlaceItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_place, parent, false)),
    View.OnClickListener {

    private var title: TextView? = null
    private var icon: ImageView? = null
    private var rated: TextView? = null

    init {
        title = itemView.findViewById(R.id.tv_history_title)
        icon = itemView.findViewById(R.id.iv_icon)
        rated = itemView.findViewById(R.id.tv_history_timestamp)
    }

    fun bind(item: PlaceItem)
    {
        title?.text = item.name

        rated?.text = "Rating : ${item.rating}"

        if(item.photos?.size == 0) {
            Picasso.get().load(item.icon).into(icon)
        } else {
            var photoRef = item.photos?.get(0)

            photoRef ?.let {

                val imageUrl = BuildConfig.BASE_URL + "maps/api/place/photo?" + "maxwidth=256&" + "photoreference=${photoRef.photo_reference}&" + "key=" + BuildConfig.BASE_KEY
                Picasso.get().load(imageUrl).into(icon)
            } ?: run {
                Picasso.get().load(item.icon).into(icon)
            }
        }
    }

    override fun onClick(p0: View?) {
        // Could nav to map or a detail page
    }
}