package com.aqrlei.sample.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.widgetcollection.recycler.FlowLayoutManager
import kotlinx.android.synthetic.main.frag_flow_recycler.*
import kotlinx.android.synthetic.main.recycler_flow_item.view.*

/**
 * Created by AqrLei on 2019-06-06
 */
class FlowRecyclerFragment : BaseFragment() {

    private val data = listOf<String>("迪","迪丽","迪丽热巴","迪丽热","迪丽热巴迪丽热巴迪丽热巴迪丽热巴迪丽热巴迪丽热巴迪丽热巴")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_flow_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recyclerView) {
            adapter = SimpleItemAdapter()
            layoutManager = FlowLayoutManager()
        }
    }

    inner class SimpleItemAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return SimpleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_flow_item,
                    parent, false
                ))
        }

        override fun getItemCount(): Int = data.size

        private fun getItem(position: Int) =
            if (position > data.size - 1 || position < 0) null else data[position]


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.tvItem.text = getItem(position)
        }
    }

    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}