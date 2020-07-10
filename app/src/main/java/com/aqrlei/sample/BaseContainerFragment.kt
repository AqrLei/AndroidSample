package com.aqrlei.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aqrlei.sample.base.BaseFragment

/**
 * created by AqrLei on 2019-12-27
 */
abstract class BaseContainerFragment : BaseFragment() {
    object ExampleType {
        const val WIDGET = 1
        const val HELPER = 2
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_main_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.title).text = getTitleText()
        view.findViewById<RecyclerView>(R.id.recyclerView).also {
            it.layoutManager = GridLayoutManager(this.context, 4)
            it.adapter =
                SimpleItemAdapter(getExampleType())
        }
    }

    abstract fun getTitleText(): String
    abstract fun getExampleType(): Int


}