package com.aqrlei.sample.scheme

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.aqrlei.helper.log.LogHelper
import com.aqrlei.sample.base.BaseActivity
import com.aqrlei.sample.PageItemDescription
import com.aqrlei.sample.R
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
        LogHelper.d(getTag(), "scheme: ${intent.data?.scheme} ")
        LogHelper.d(getTag(), "host: ${intent.data?.host} ")
        LogHelper.d(getTag(), "path: ${intent.data?.path} ")
        LogHelper.d(getTag(), "pathSegments: ${intent.data?.pathSegments} ")
        LogHelper.d(getTag(), "encodedPath: ${intent.data?.encodedPath} ")
        LogHelper.d(getTag(), "lastPathSegment: ${intent.data?.lastPathSegment} ")
        LogHelper.d(getTag(), "query: ${intent.data?.query} ")
        LogHelper.d(getTag(), "queryName: ${intent.data?.queryParameterNames} ")

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