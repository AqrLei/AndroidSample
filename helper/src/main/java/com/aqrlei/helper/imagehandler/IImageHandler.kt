package com.aqrlei.helper.imagehandler

import android.graphics.Bitmap
import com.aqrlei.helper.imagehandler.decorator.IImageDecorator
import com.aqrlei.helper.imagehandler.loader.IImageLoader

/**
 * Created by AqrLei on 2019-06-03
 */

interface IImageHandler {

    fun load(path: String = "", id: Int = -1): IImageHandler
    fun decorate(decorator: IImageDecorator): IImageHandler
    fun decorate(decorator: List<IImageDecorator>): IImageHandler
    fun setCallback(loadCallback: ((Bitmap?) -> Unit)? = null, decorateCallback: ((Bitmap?,Int) -> Unit)? = null)
    fun display(onDisplay: (Bitmap?) -> Unit, width: Float = 0F, height: Float = 0F)

    interface Builder {
        fun loadFrom(loader: IImageLoader<*>): Builder
        fun build(): IImageHandler
    }
}