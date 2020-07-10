package com.aqrlei.helper.imagehandler.loader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import com.aqrlei.helper.CacheFileHelper
import com.aqrlei.helper.StringHelper
import com.aqrlei.helper.cache.lru.ICacheTask
import com.aqrlei.helper.imagehandler.BitmapHelper

/**
 * Created by AqrLei on 2019-06-05
 */
abstract class AbstractImageLoader<T>(protected val imageCacheTask: ICacheTask? = null) :
    IImageLoader<T> {

    companion object {
        @JvmStatic
        protected val cacheBitmapMemory: LruCache<String, Bitmap> =
            object : LruCache<String, Bitmap>((CacheFileHelper.runtimeMaxMemory() / 20).toInt()) {
                override fun sizeOf(key: String, value: Bitmap): Int {
                    return value.byteCount
                }
            }
    }

    protected fun dp2px(dpValue: Float): Float {
        return BitmapHelper.dp2px(dpValue)
    }

    protected fun getAdaptedBitmapSampleSize(
        options: BitmapFactory.Options,
        targetWidth: Float,
        targetHeight: Float
    ): Int {
        return BitmapHelper.getAdaptedBitmapSampleSize(options, targetWidth, targetHeight)
    }

    protected fun generateMemoryKey(key: String, width: Float, height: Float): String {
        return StringHelper.md5Encode("$width$key$height")
    }

    protected fun generateMemoryKey(key: String, width: Int, height: Int): String {
        return StringHelper.md5Encode("$width$key$height")
    }
}