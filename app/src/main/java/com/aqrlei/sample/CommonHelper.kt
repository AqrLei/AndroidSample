package com.aqrlei.sample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aqrlei.sample.helper.HelperFragment
import com.aqrlei.sample.scheme.Helper
import com.aqrlei.sample.scheme.Widget
import com.aqrlei.sample.widget.WidgetFragment
import java.util.*

/**
 * created by AqrLei on 2020/5/20
 */
object CommonHelper {

    fun getFragPageAdapter(fm: FragmentManager) = FragPageAdapter(fm).apply {
        add(FragInfo("Widget", WidgetFragment::class.java.newInstance()))
        add(FragInfo("Helper", HelperFragment::class.java.newInstance()))
    }

    fun assembleHelperList() = listOf(
        PageItemDescription(Helper.IGNORE_BATTERY, "IgnoreBattery"),
        PageItemDescription(Helper.IMAGE, "ImageHandler"),
        PageItemDescription(Helper.PERMISSION, "PermissionHelper"),
        PageItemDescription(Helper.NET, "Net"),
        PageItemDescription(Helper.FILE, "File"),
        PageItemDescription(Helper.TEST, "Test")
    )

    fun assembleWidgetList() = listOf(
        PageItemDescription(Widget.BANNER_DISPATCH, "BannerDispatch"),
        PageItemDescription(Widget.GUIDE, "Guide"),
        PageItemDescription(Widget.TEXT_SPAN, "TextSpan"),
        PageItemDescription(Widget.SMOOTH_SLIDE, "SmoothSlide"),
        PageItemDescription(Widget.AUTO_DRAG, "AutoDrag"),
        PageItemDescription(Widget.SHADOW, "Shadow"),
        PageItemDescription(Widget.FLOW_RECYCLER, "Flow")
    )
}

data class FragInfo(
    var name: String,
    var frag: Fragment
)

class FragPageAdapter(manager: FragmentManager) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    private var data: MutableList<FragInfo> = ArrayList<FragInfo>()

    override fun getItem(position: Int): Fragment {
        return data[position].frag
    }

    fun add(fragInfo: FragInfo) {
        data.add(fragInfo)
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].name
    }

}