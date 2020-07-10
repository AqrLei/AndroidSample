package com.aqrlei.sample.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aqrlei.sample.R
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.sample.PageItemDescription
import com.aqrlei.sample.scheme.Widget
import kotlinx.android.synthetic.main.view_frag_banner_dispatch.*

/**
 * created by AqrLei on 2019-12-13
 */

class BannerDispatchFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_frag_banner_dispatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBanner.setOnClickListener {
            PageItemDescription(Widget.BANNER, "Banner", 2).router(this.context)
        }
        tvBanner2.setOnClickListener {
            PageItemDescription(Widget.BANNER2, "Banner2", 2).router(this.context)
        }
        tvBannerTest.setOnClickListener {
            PageItemDescription(
                Widget.BANNER_TEST,
                "BannerTest",
                2
            ).router(this.context)
        }
    }
}
