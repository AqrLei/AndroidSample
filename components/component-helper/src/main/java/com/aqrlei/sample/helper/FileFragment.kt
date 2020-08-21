package com.aqrlei.sample.helper

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aqrlei.utilcollection.ext.coroutineCancellableRunLazy
import com.aqrlei.utilcollection.ext.coroutineRunLazy
import com.aqrlei.litelog.LogHelper
import com.aqrlei.litenet.OkHttpHelper
import com.aqrlei.litenet.OkHttpRequest
import com.aqrlei.litenet.exception.CancelException
import com.aqrlei.litenet.transformer.common.MediaUriTransFormer
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.sample.base.ImageHelper
import com.aqrlei.utilcollection.CacheFileUtil
import com.aqrlei.utilcollection.ext.toMd5
import com.aqrlei.utilcollection.toast.ToastHelper
import kotlinx.android.synthetic.main.frag_file.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import java.io.File

/**
 * created by AqrLei on 2020/3/16
 */
class FileFragment : BaseFragment(), View.OnClickListener {

    private var reqCode = 1001

    private val downloadPng = "https://mktv-in-cdn.mockuai.com/15855327939374961.png"
    private val downloadMp4 = "https://wdzg-file-test-cdn.mockuai.com/15856357970387769.mp4"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_file, container, false)
    }

    private var lazyJob: Job? = null
    private var cancellableJob: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSaveBitmap.setOnClickListener(this)
        tvPick.setOnClickListener(this)
        tvGetContent.setOnClickListener(this)
        tvDocument.setOnClickListener(this)
        tvMediaStoreImage.setOnClickListener(this)
        tvMediaStoreAudio.setOnClickListener(this)
        tvMediaStoreVideo.setOnClickListener(this)

        lazyJob = coroutineRunLazy(backgroundBlock = {
            LogHelper.d("AqrLei", "background - thread: ${Thread.currentThread()}")
            "this is just a test"
        }, resultCallback = { result ->
            LogHelper.d("AqrLei", "callback - thread : ${Thread.currentThread()}")
            LogHelper.d("AqrLei", "callback - result: ${result}")
        })
        cancellableJob = coroutineCancellableRunLazy(
            backgroundBlock = {
                LogHelper.d("AqrLei", "background - thread: ${Thread.currentThread()}")
                delay(3000)
                LogHelper.d("AqrLei", "background - delay 3m thread: ${Thread.currentThread()}")
                "this is just a cancelable test"
            },
            cancelBlock = {
                LogHelper.d("AqrLei", "cancel - thread : ${Thread.currentThread()}")
            },
            resultCallback = { result ->
                LogHelper.d("AqrLei", "result - thread : ${Thread.currentThread()}")
                LogHelper.d("AqrLei", "result- result: ${result}")
            },
            errorCallback = { e ->
                LogHelper.d(
                    "AqrLei",
                    "cancellable - cancel: ${e is CancelException} msg: ${e.message ?: ""}")
            }
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvSaveBitmap -> {
                val bitmap = Bitmap.createBitmap(llBg.width, llBg.height, Bitmap.Config.RGB_565)
                val canvas = Canvas(bitmap)
                llBg.draw(canvas)
                context?.let {
                    val file = File.createTempFile(
                        "bitmapllbg",
                        ".jpg",
                        CacheFileUtil.getAppFilesDirFile(it, "images"))
                    CacheFileUtil.saveBitmap(bitmap, file) { resultFile ->
                        resultFile?.let {
                            ToastHelper.shortShow("${resultFile.absolutePath} 保存成功")
                        }
                    }
                }

            }
            R.id.tvPick -> {
                CacheFileUtil.pickFileItem(null, this, reqCode, "image/*")

                lazyJob?.start()
                cancellableJob?.start()
//                cancellableJob?.cancel()
            }
            R.id.tvGetContent -> {
                CacheFileUtil.getFileContent(null, this, reqCode, "image/*")
            }
            R.id.tvDocument -> {
                CacheFileUtil.performFileSearch(null, this, reqCode)
            }
            R.id.tvMediaStoreImage -> {
                context?.let { ct ->
                    val fileName = "${downloadPng.toMd5()}.png"
                    val uri = CacheFileUtil.createMediaStoreImageUri(
                        ct.contentResolver,
                        fileName,
                        "image/png",
                        fileName)
                    LogHelper.d("AqrLei", "fileName: $fileName uri: $uri")
                    download(object : OkHttpRequest.MediaFileUriCallback() {
                        override fun onFinish(data: Uri?, e: Throwable?) {
                            super.onFinish(data, e)
                            data?.let {
                                ToastHelper.shortShow(" onFinish \n uri : $it \n}")
                                LogHelper.d("AqrLei", " onFinish \n uri : $it \n}")
                                ImageHelper.getBitmapFromUri(ct, it) { bitmap ->
                                    llBg.background = BitmapDrawable(bitmap)
                                }
                            }
                            e?.let {
                                ToastHelper.shortShow(" onFinish \n error : ${it.message}")
                                LogHelper.d("AqrLei", "onFinish - error : ${it.message}")
                            }
                        }
                    }, uri, downloadPng)
                }
            }
            R.id.tvMediaStoreAudio -> {

            }
            R.id.tvMediaStoreVideo -> {
                context?.let { ct ->
                    val fileName = "${downloadMp4.toMd5()}.mp4"
                    val uri = CacheFileUtil.createMediaStoreVideoUri(
                        ct.contentResolver,
                        fileName,
                        "video/mp4",
                        fileName,
                        "Movies/${ct.packageName}"
                    )
                    download(object : OkHttpRequest.MediaFileUriCallback() {
                        override fun onFinish(data: Uri?, e: Throwable?) {
                            super.onFinish(data, e)
                            data?.let {
                                ToastHelper.shortShow(" onFinish \n uri : $it \n}")
                                LogHelper.d("AqrLei", " onFinish \n uri : $it \n}")
                                ImageHelper.getBitmapFromUri(ct, it) { bitmap ->
                                    llBg.background = BitmapDrawable(bitmap)
                                }
                            }

                            e?.let {
                                ToastHelper.shortShow(" onFinish \n error : ${it.message}")
                                LogHelper.d("AqrLei", "onFinish - error : ${it.message}")
                            }
                        }
                    }, uri, downloadMp4)
                }
            }
        }
    }

    private fun download(callback: OkHttpRequest.DownloadCallback<Uri>, uri: Uri?, url: String) {
        context?.let {
            if (uri == null) {
                ToastHelper.shortShow("uri is null")
                return
            }
            val transformer = MediaUriTransFormer(uri, it.contentResolver, callback)
            OkHttpHelper.download(url, Uri::class.java, callback, transformer)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == reqCode && resultCode == Activity.RESULT_OK) {
            context?.let {
                LogHelper.d("AqrLei", "${data?.data}")
                ImageHelper.getBitmapFromUri(it, data?.data) { bitmap ->
                    llBg.background = BitmapDrawable(bitmap)
                }
                LogHelper.d("AqrLei", "path : ${getPath(it.contentResolver, data?.data)}")
            }
        }
    }

    private fun getPath(contentResolver: ContentResolver, uri: Uri?): String {
        var result = ""
        uri ?: return result
        contentResolver.query(uri, null, null, null, null, null)?.use {
            if (it.moveToFirst()) {
                for (item in it.columnNames) {
                    result += "$item: ${it.getString(it.getColumnIndex(item))}\n"
                }
            }
        }
        return result
    }
}