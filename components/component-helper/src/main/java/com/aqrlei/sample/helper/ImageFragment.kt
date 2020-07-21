package com.aqrlei.sample.helper

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.aqrlei.cache.lru.LruCacheConfig
import com.aqrlei.cache.lru.LruCacheHelper
import com.aqrlei.imagehandler.ImageHandleHelper
import com.aqrlei.imagehandler.decorator.ShapeImageDecorator
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.util.CacheFileUtil
import kotlinx.android.synthetic.main.frag_imagehandler.*

/**
 * Created by AqrLei on 2019-06-06
 */
class ImageFragment : BaseFragment() {

    private val sourceUrl =
        "http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg"
    private var localSourceUrl = ""

    private val shapeImageDecorator = ShapeImageDecorator()
        .asCircle(true)
        .setBorders(Color.parseColor("#FF0000"), 3F)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_imagehandler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        res.setOnClickListener(this::loadImageFromRes)
        qrcode.setOnClickListener(this::loadQRCodeImage)
        local.setOnClickListener(this::loadLocalFileImage)
        remote.setOnClickListener(this::loadRemoteImage)
        clearCache.setOnClickListener(::clearCache)
    }

    private fun loadImageFromRes(v: View) {
        ImageHandleHelper.display(R.drawable.ic_agree, ivTest, 80F, 80F, shapeImageDecorator)
    }

    private fun loadQRCodeImage(v: View) {
        val url = "https://www.baidu.com"
        ImageHandleHelper.displayQRCode(
            url, ivTest, 200F, 200F,
            shapeImageDecorator.getBitmap(localSourceUrl, 80F, 80F)
        )
    }

    private val reqImgCode = 0x11
    private fun loadLocalFileImage(v: View) {
        CacheFileUtil.getFileContent(null, this, reqImgCode, "image/*")
    }

    private fun loadRemoteImage(v: View) {
        loadImageFromUrl(sourceUrl)
    }

    private fun loadImageFromUrl(url: String) {
        ImageHandleHelper.display(url, ivTest, 80F, 80F, shapeImageDecorator)
    }

    private fun clearCache(v: View) {
        LruCacheHelper.clearCache(LruCacheConfig.DEFAULT_CACHE_DISK_NAME)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == reqImgCode && resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.run {
                localSourceUrl = this.toString()
                loadImageFromUrl(this.toString())
            }
        }
    }
}