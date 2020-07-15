package com.aqrlei.sample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.aqrlei.sample.base.page.IPage
import com.aqrlei.sample.base.page.referPageName

/**
 * created by AqrLei on 2020/5/22
 */
abstract class BaseActivity : AppCompatActivity(), IPage {

    private var onVisibleTime: Long = 0

    @LayoutRes
    abstract fun getLayoutId(): Int

    fun getTag() = this.javaClass.simpleName

    override fun getPageName(): String = getTag()

    override fun getReferPageName(): String = referPageName ?: getPageName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun onResume() {
        super.onResume()
        onVisibleTime = System.currentTimeMillis()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        referPageName = getPageName()
    }
}