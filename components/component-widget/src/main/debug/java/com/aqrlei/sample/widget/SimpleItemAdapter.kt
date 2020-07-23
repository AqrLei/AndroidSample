package com.aqrlei.sample.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleItemAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<PageItemDescription>()

    init {
        data.addAll(assembleWidgetList())
    }

    private fun assembleWidgetList() = listOf(
        PageItemDescription(Widget.BANNER, "Banner"),
        PageItemDescription(Widget.BANNER2, "Banner2"),
        PageItemDescription(Widget.BANNER_TEST, "BannerTest"),
        PageItemDescription(Widget.GUIDE, "Guide"),
        PageItemDescription(Widget.TEXT_SPAN, "TextSpan"),
        PageItemDescription(Widget.SMOOTH_SLIDE, "SmoothSlide"),
        PageItemDescription(Widget.AUTO_DRAG, "AutoDrag"),
        PageItemDescription(Widget.SHADOW, "Shadow"),
        PageItemDescription(Widget.FLOW_RECYCLER, "Flow")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SimpleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.example_recycler_item, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text).text = getItem(position)?.mKitName
        holder.itemView.setOnClickListener {
            getItem(position)?.router(it.context)
        }
    }

    private fun getItem(position: Int) = if (position > data.size - 1) null else data[position]

    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

