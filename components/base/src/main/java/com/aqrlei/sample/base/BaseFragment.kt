package com.aqrlei.sample.base

import androidx.fragment.app.Fragment
import com.aqrlei.sample.base.page.IPage
import com.aqrlei.sample.base.page.referPageName

/**
 * created by AqrLei on 2020/5/15
 */
open class BaseFragment : Fragment(), IPage {

    override fun getPageName(): String = this.javaClass.simpleName

    override fun getReferPageName(): String = referPageName ?: getPageName()

    override fun onPause() {
        referPageName = getPageName()
        super.onPause()
    }
}