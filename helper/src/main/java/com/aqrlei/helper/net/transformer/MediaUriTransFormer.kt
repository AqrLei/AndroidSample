package com.aqrlei.helper.net.transformer

import android.content.ContentResolver
import android.net.Uri
import com.aqrlei.helper.CacheFileHelper
import com.aqrlei.helper.net.IProgress
import com.aqrlei.helper.net.ITransformer
import okhttp3.ResponseBody
import java.lang.reflect.Type

/**
 * created by AqrLei on 2020/4/9
 */
class MediaUriTransFormer(
    private val uri: Uri,
    private val contentResolver: ContentResolver,
    private val processCallback: IProgress) :
    AbstractTransformer<Uri>() {

    override fun transformer(response: ResponseBody, resultType: Type): Uri{
        val inputStream = response.byteStream()
        inputStream.use {
            CacheFileHelper.alertDocument(contentResolver, uri) { fos ->
                val buffer = ByteArray(2048)
                var len: Int
                val totalLength = response.contentLength()
                var currentLength = 0L
                while (inputStream.read(buffer).apply { len = this } > 0) {
                    fos.write(buffer, 0, len)
                    fos.flush()
                    currentLength += len.toLong()
                    log("currentLength : ${currentLength}, totalLength : $totalLength")
                    if (totalLength > 0) {
                        processCallback.onProgress(
                            currentLength,
                            totalLength,
                            currentLength == totalLength)
                    }
                }
            }
        }
        return uri
    }
}