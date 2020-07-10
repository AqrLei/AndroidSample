package com.aqrlei.sample.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleItemAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<PageItemDescription>()

    init {
        data.addAll(assembleHelperList())
    }



    private fun assembleHelperList() = listOf(
        PageItemDescription(Helper.IGNORE_BATTERY, "IgnoreBattery"),
        PageItemDescription(Helper.IMAGE, "ImageHandler"),
        PageItemDescription(Helper.PERMISSION, "PermissionHelper"),
        PageItemDescription(Helper.NET, "Net"),
        PageItemDescription(Helper.FILE, "File"),
        PageItemDescription(Helper.TEST, "Test")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SimpleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.example_recycler_item,
                parent,
                false
            )
        )
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

