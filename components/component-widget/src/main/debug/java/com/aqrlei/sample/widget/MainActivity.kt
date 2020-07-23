package com.aqrlei.sample.widget

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.aqrlei.sample.base.BaseActivity
import kotlinx.android.synthetic.main.widget_activity_main.*

/**
 * created by AqrLei on 2020/7/9
 */
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.widget_activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = SimpleItemAdapter()
    }
}