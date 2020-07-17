package com.aqrlei.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aqrlei.sample.BaseContainerFragment.ExampleType
import com.aqrlei.widget.ext.setOnAvoidFastClickListener

class SimpleItemAdapter(type: Int?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<PageItemDescription>()

    init {
        data.addAll(
            when (type) {
                ExampleType.WIDGET -> assembleWidgetList()
                ExampleType.HELPER -> assembleHelperList()
                else -> emptyList()
            })
    }

    private fun assembleWidgetList() = CommonHelper.assembleWidgetList()

    private fun assembleHelperList() = CommonHelper.assembleHelperList()

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
        holder.itemView.setOnAvoidFastClickListener {
            getItem(position)?.router(it.context)
        }
    }

    private fun getItem(position: Int) = if (position > data.size - 1) null else data[position]

    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

