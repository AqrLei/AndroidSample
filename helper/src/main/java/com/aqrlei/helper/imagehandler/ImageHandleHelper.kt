package com.aqrlei.helper.imagehandler

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.aqrlei.helper.cache.lru.LruCacheHelper
import com.aqrlei.helper.imagehandler.decorator.IImageDecorator
import com.aqrlei.helper.imagehandler.loader.QRImageLoader
import com.aqrlei.helper.imagehandler.loader.ResImageLoader
import com.aqrlei.helper.imagehandler.loader.SourceImageLoader
import com.aqrlei.helper.cache.lru.task.SimpleCacheTask
import java.lang.ref.WeakReference

/**
 * created by AqrLei on 2020-01-04
 */
object ImageHandleHelper {
    private val qrImageLoader: QRImageLoader by lazy { QRImageLoader() }
    private val resImageLoader: ResImageLoader? by lazy {
        reference?.get()?.let {
            ResImageLoader(it.resources)
        }
    }
    private val sourceImageLoader: SourceImageLoader? by lazy {
        reference?.get()?.let {
            SourceImageLoader(it,
                SimpleCacheTask(), LruCacheHelper.getDefaultCache(it))
        }
    }
    private var reference: WeakReference<Context>? = null
    fun init(context: Context) {
        reference = WeakReference(context)
    }

    fun display(
        url: String,
        imageView: ImageView,
        width: Float = 0F,
        height: Float = 0F,
        decorator: IImageDecorator? = null) {
        sourceImageLoader?.let { loader ->
            ImageHandler.Builder().loadFrom(loader).build().run {
                load(path = url)
                decorator?.let { decorate(decorator) }
                display({
                    imageView.setImageBitmap(it)
                }, width, height)
            }
        }

    }

    fun display(
        resId: Int,
        imageView: ImageView,
        width: Float = 0F,
        height: Float = 0F,
        decorator: IImageDecorator? = null) {
        resImageLoader?.let { loader ->
            ImageHandler.Builder().loadFrom(loader).build().run {
                load(id = resId)
                decorator?.let { decorate(decorator) }
                display({
                    imageView.setImageBitmap(it)
                }, width, height)
            }
        }
    }

    fun displayQRCode(
        url: String,
        imageView: ImageView,
        width: Float,
        height: Float,
        logo: Bitmap? = null,
        ratio: Float = 0.25F,
        decorator: IImageDecorator? = null) {
        ImageHandler.Builder().loadFrom(qrImageLoader.setLogo(logo, ratio))
            .build().run {
                load(path = url)
                decorator?.let { decorate(decorator) }
                display({
                    imageView.setImageBitmap(it)
                }, width, height)
            }
    }
}