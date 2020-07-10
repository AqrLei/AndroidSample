package com.aqrlei.helper.imagehandler.decorator

import android.graphics.Bitmap
import androidx.collection.LruCache
import com.aqrlei.helper.CacheFileHelper
import com.aqrlei.helper.StringHelper
import com.aqrlei.helper.imagehandler.BitmapHelper

/**
 * Created by AqrLei on 2019-06-05
 */

abstract class AbstractImageDecorator :
    IImageDecorator {
    companion object {
        protected const val ACCURACY_RATE = 0.02
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

    protected fun isSuitable(rW: Float, rH: Float, tW: Float, tH: Float): Boolean {
        return BitmapHelper.isSuitable(rW, rH, tW, tH, ACCURACY_RATE)
    }

    protected fun cropBitmap(bitmap: Bitmap, tW: Float, tH: Float): Bitmap {
        return BitmapHelper.cropBitmap(bitmap, tW, tH)
    }

    protected fun generateMemoryKey(key: String, width: Float, height: Float): String {
        return StringHelper.md5Encode("$width$key$height")
    }

    override fun getBitmap(key: String, width: Float, height: Float): Bitmap? {
        return cacheBitmapMemory.get(generateMemoryKey(key, width, height))
    }
}