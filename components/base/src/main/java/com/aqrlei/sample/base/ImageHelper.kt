package com.aqrlei.sample.base

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.aqrlei.util.CacheFileUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by AqrLei on 2019-05-30
 */
object ImageHelper {

    //TODO
    @JvmStatic
    fun getBitmapFromUri(context: Context, uri: Uri?, callback: (bitmap: Bitmap?) -> Unit) {
        uri ?: return
        GlobalScope.launch(Dispatchers.IO) {
            CacheFileUtil.readFileFromUri(context.contentResolver, uri) {
                val bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
                GlobalScope.launch(Dispatchers.Main) {
                    callback(bitmap)
                }
            }
        }
    }
}