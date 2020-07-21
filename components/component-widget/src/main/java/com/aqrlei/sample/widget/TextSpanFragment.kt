package com.aqrlei.sample.widget

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.util.DensityUtil
import com.aqrlei.widget.text.BackgroundShapeSpan
import kotlinx.android.synthetic.main.frag_textspan.*

/**
 * Created by AqrLei on 2019-06-06
 */
class TextSpanFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_textspan, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val padding = DensityUtil.dp2px(1F)
        tvSpan.text =
            SpannableString("秒杀，花好月圆超好吃的月饼花好月圆超好吃的月饼花好月圆超好吃的月饼花好月圆超好吃的月饼花好月圆超好吃的月饼花好月圆超好吃的月饼").also {
                it.setSpan(
                    BackgroundShapeSpan(
                        Color.WHITE,
                        Color.RED,
                        Color.GREEN,
                        DensityUtil.dp2px(2F),
                        DensityUtil.dp2px(5F).toFloat(),
                        padding,
                        padding,
                        BackgroundShapeSpan.Style.FILL
                    ),
                    0,
                    2,
                    SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
                )
                it.setSpan(
                    AbsoluteSizeSpan(18, true),
                    0,
                    2,
                    SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }
    }
}