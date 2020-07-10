package com.aqrlei.sample.widget.banner

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.aqrlei.helper.log.LogHelper
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.sample.widget.R
import com.aqrlei.bannerview.widget.BannerView
import kotlinx.android.synthetic.main.view_banner_item.view.*

/**
 * created by AqrLei on 2019-12-13
 */

class BannerFragment : BaseFragment() {
    private val des = arrayOf("", "", "", "", "")

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
        return inflater.inflate(R.layout.view_frag_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager(view)
    }

    private fun setupViewPager(view: View) {
        getBannerViewList(view).forEach {
            it.adapter = BannerView.Adapter(SimpleBannerViewHolder()).also { adapter ->
                adapter.pageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(position: Int) {
                        LogHelper.d("AqrLei", "change: position - $position")
                    }
                }
                adapter.setPageClickListener(object : BannerView.Adapter.OnPageClickListener {
                    override fun onPageClick(v: View, position: Int) {
                        LogHelper.d("AqrLei", "click: position-$position")
                    }
                })
            }
        }
    }

    private fun getBannerViewList(v: View) = arrayListOf<BannerView>(
        v.findViewById(R.id.bannerViewNormal),
        v.findViewById(R.id.bannerViewSmooth),
        v.findViewById(R.id.bannerViewWorm),
        v.findViewById(R.id.bannerViewDashNormal),
        v.findViewById(R.id.bannerViewDashSmooth),
        v.findViewById(R.id.bannerViewDashWorm),
        v.findViewById(R.id.bannerViewCircleNormal),
        v.findViewById(R.id.bannerViewCircleSmooth),
        v.findViewById(R.id.bannerViewCircleWorm),
        v.findViewById(R.id.bannerViewWithoutIndicator),
        v.findViewById(R.id.bannerViewBelow)
    )

    inner class SimpleBannerViewHolder : BannerView.ViewHolder() {
        override fun getLayoutId(viewType: Int): Int = R.layout.view_banner_item

        override fun getItemCount(): Int = list.size
        override fun onViewCreate(view: View, parent: ViewGroup, position: Int): View {
            return view.apply {
                view.tvBanner.setBackgroundColor(list[position].color)
                view.tvBanner.text = "$position"
                view.requestLayout()

                LogHelper.d(
                    "AqrLei",
                    "bindView - viewHeight : ${view.height}, viewWidth : ${view.width}"
                )
            }
        }
    }

}
