package com.aqrlei.sample.widget

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.aqrlei.helper.DensityHelper
import com.aqrlei.helper.log.LogHelper
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.guide.GuideManager
import com.aqrlei.guide.core.Controller
import com.aqrlei.guide.core.Hole
import kotlinx.android.synthetic.main.frag_guide.*

/**
 * Created by AqrLei on 2019-06-06
 */
class GuideFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testGuideCover()
    }

    private var controller: Controller? = null
    private fun testGuideCover() {
        controller = GuideManager.with(this) {
            onGuideChanged(
                onShowed = {
                    log("onGuideChanged - onShowed - ${it.isGuidePageShow()}")
                },
                onRemoved = {
                    log("onGuideChanged - onRemoved - ${it.isGuidePageShow()}")
                })
            guidePage {
                overlay {
                    everywhereCancelable { false }
                    backgroundColor = Color.parseColor("#CC000000")
                    layoutResId = R.layout.layout_guide_test
                    enterAnimation = AnimationUtils.loadAnimation(
                        this@GuideFragment.context,
                        android.R.anim.slide_in_left
                    )
                    exitAnimation = AnimationUtils.loadAnimation(
                        this@GuideFragment.context,
                        android.R.anim.slide_out_right
                    )
                    onOverlayInflatedListener { view, controller ->

                        log("onOverlayInflated")
                        view.findViewById<View>(R.id.tvHello).setOnClickListener {
                            log("onOverlayInflated - childClick")
                            controller.dismiss()
                        }
                    }
                }
                hole {
                    holeView = shadowLayout
                    setPadding(DensityHelper.dip2px(5F))
                    shape = Hole.Shape.CIRCLE
                    onHighlightDrewListener { canvas, rectF ->
                        log("onHighlightDrew - ${controller?.isGuidePageShow()}")
                    }
                    setOnClickListener { _, _ ->
                        log("Hole click")
                    }
                }
                overlayWithHoleDrawListener { controller, view, canvas, rectF ->
                    log("overlayWithHoleDraw- ${controller?.isGuidePageShow()}")
                }
            }
        }.build()
        //controller?.show()
        shadowLayout.setOnClickListener {
            controller?.show()
        }
    }

    private fun log(msg: String) {
        LogHelper.d("Guide", "$msg , time : ${System.currentTimeMillis()}")
    }
}