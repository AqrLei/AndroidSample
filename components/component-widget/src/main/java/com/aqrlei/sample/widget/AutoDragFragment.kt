package com.aqrlei.sample.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.widgetcollection.AutoDragLayout
import kotlinx.android.synthetic.main.frag_auto_drag.*

/**
 * Created by AqrLei on 2019-06-06
 */
class AutoDragFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_auto_drag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AutoDragLayout.enableDebug(true)
        tvTest.setOnClickListener {
            Toast.makeText(this.context, "Hello World", Toast.LENGTH_SHORT).show()
        }
    }


}