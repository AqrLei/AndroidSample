package com.aqrlei.sample.widget.banner

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.aqrlei.litelog.LogHelper
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.sample.widget.R
import com.aqrlei.bannerview.widget.indicator.FigureIndicatorView
import com.aqrlei.bannerview.widget.banner2.BannerView2
import com.aqrlei.utilcollection.DensityUtil
import kotlinx.android.synthetic.main.view_banner_item.view.*

/**
 * created by AqrLei on 2019-12-13
 */

class Banner2Fragment : BaseFragment() {

    private val des = arrayOf("你", "是", "只", "啥", "啊")

    private val colorList: MutableList<Int> by lazy {
        ArrayList<Int>().also {
            it.add(Color.GREEN)
            it.add(Color.BLUE)
            it.add(Color.YELLOW)
            it.add(Color.CYAN)
            it.add(Color.MAGENTA)
        }

    }
    private val list: MutableList<BannerBean> by lazy {
        ArrayList<BannerBean>().also {
            for (i in 0..4) {
                it.add(BannerBean(colorList[i], des[i]))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_frag_banner2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBannerViewPager(view)
    }

    private fun setUpBannerViewPager(view: View) {
        getBannerViewList(view).forEach {
            it.adapter = BannerView2.Adapter(SimpleBannerViewHolder()).also { adapter ->
                adapter.pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        LogHelper.d("AqrLei", "Banner2 - selected position : ${position}")
                    }
                }
                adapter.pageClickCallback = object : BannerView2.Adapter.OnPageClickCallback() {
                    override fun onClick(view: View, position: Int) {
                        LogHelper.d("AqrLei", "Banner2 - clicked position : ${position}")
                    }
                }
            }
        }
    }

    private fun getBannerViewList(v: View) = arrayListOf<BannerView2>(
        v.findViewById(R.id.bannerView2Gone),
        v.findViewById<BannerView2>(R.id.bannerView2Below).apply {
            indicatorView = FigureIndicatorView(context).also {
                val dp6 = DensityUtil.dp2px(6F)
                it.setPadding(dp6, dp6 / 2, dp6, dp6 / 2)
            }
        },
        v.findViewById<BannerView2>(R.id.bannerView2Inside).apply {
            indicatorView = FigureIndicatorView(context).also {
                val dp6 = DensityUtil.dp2px(6F)
                it.setPadding(dp6, dp6 / 2, dp6, dp6 / 2)
            }
        },
        v.findViewById(R.id.bannerView2InsideAccordion),
        v.findViewById(R.id.bannerView2InsideStack),
        v.findViewById(R.id.bannerView2InsideDepth),
        v.findViewById(R.id.bannerView2InsideZoomOut),
        v.findViewById(R.id.bannerView2InsideMulti),
        v.findViewById(R.id.bannerView2InsideMultiOverlap)
    )

    inner class SimpleBannerViewHolder : BannerView2.ViewHolder() {
        override fun getLayoutId(viewType: Int): Int = R.layout.view_banner_item

        override fun getItemCount(): Int = list.size

        override fun bindView(position: Int, view: View) {
            view.tvBanner.setBackgroundColor(list[position].color)
            view.tvBanner.text = "$position"
        }
    }

}
