package com.akadatsky.gif4work.presentation.gifslist

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.akadatsky.gif4work.R
import com.akadatsky.gif4work.data.remote.Data
import com.bumptech.glide.Glide

class GifAdapter(private val activity: Activity) : RecyclerView.Adapter<GifViewHolder>() {

    var results: List<Data> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view: View = activity.layoutInflater.inflate(R.layout.gif_list_item, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val result: Data = results[position]
        val uri = result.images.original.url
        Glide
            .with(holder.itemView.context)
            .load(uri)
            .thumbnail(Glide.with(activity).load(R.drawable.gif_placeholder))
            .into(holder.gifView);
    }

    override fun getItemId(i: Int): Long = i.toLong()
    override fun getItemCount(): Int = results.size
}

class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gifView: ImageView = itemView.findViewById(R.id.gif_view)
}