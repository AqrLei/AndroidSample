package com.aqrlei.sample.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.aqrlei.helper.log.LogHelper
import com.aqrlei.sample.base.BaseActivity
import com.aqrlei.util.AppUtil
import java.util.*

/**
 * created by AqrLei on 2020/5/22
 */
class SchemeActivity : BaseActivity() {

    companion object {

        fun schemeRouter(context: Context?, path: String, data: PageItemDescription) {
            context?.let {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(path)).putExtra(KEY_PAGE_DATA, data)
                if (AppUtil.queryActivities(it, intent)) {
                    context.startActivity(intent)
                }
            }
        }
    }

    private val backStack = Stack<PageItemDescription>()
    private var lastPageItemDes: PageItemDescription? = null

    override fun getLayoutId(): Int = R.layout.scheme_act

    override fun onBackPressed() {
        try {
            backStack.pop()?.router(this@SchemeActivity) ?: super.onBackPressed()
        } catch (e: Exception) {
            super.onBackPressed()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        LogHelper.d(getTag(), "onNewIntent")
        routerScheme()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routerScheme()
    }

    private fun routerScheme() {
        val uri = intent.data
        intent.extras?.getParcelable<PageItemDescription>(KEY_PAGE_DATA)?.let {
            if (it.level > lastPageItemDes?.level ?: 1) backStack.push(lastPageItemDes)
            lastPageItemDes = it
        }
        SchemeHelper.schemeRouter(this, R.id.container, uri)
    }

    override fun onDestroy() {
        backStack.clear()
        lastPageItemDes = null
        super.onDestroy()
    }
}