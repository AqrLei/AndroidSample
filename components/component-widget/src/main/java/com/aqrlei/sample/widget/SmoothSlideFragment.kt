package com.aqrlei.sample.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aqrlei.helper.log.LogHelper
import com.aqrlei.sample.base.BaseFragment
import kotlinx.android.synthetic.main.frag_smooth_slide.*

/**
 * Created by AqrLei on 2019-06-06
 */
class SmoothSlideFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_smooth_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvScrollTest.setOnClickListener {
            LogHelper.d("AqrLei", "test left Scroll")
        }

        tvScrollBottomTest.setOnClickListener {
            LogHelper.d("AqrLei", "test bottom Scroll")
        }
        tvScrollTopTest.setOnClickListener {
            LogHelper.d("AqrLei", "test top Scroll")
        }
        tvScrollRightTest.setOnClickListener {
            LogHelper.d("AqrLei", "test right  Scroll")
        }

    }


}