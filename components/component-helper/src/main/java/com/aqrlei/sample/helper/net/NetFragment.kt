package com.aqrlei.sample.helper.net

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aqrlei.sample.base.ImageHelper
import com.aqrlei.utilcollection.ext.coroutineRun
import com.aqrlei.litelog.LogHelper
import com.aqrlei.litenet.IHttpCallback
import com.aqrlei.litenet.OkHttpHelper
import com.aqrlei.litenet.OkHttpRequest
import com.aqrlei.litenet.transformer.common.FileTransformer
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.sample.helper.R
import com.aqrlei.utilcollection.CacheFileUtil
import com.aqrlei.utilcollection.toast.ToastHelper
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.frag_net.*
import okhttp3.Response
import java.io.File
import java.lang.reflect.Type

/**
 * created by AqrLei on 2020/3/16
 */
class NetFragment : BaseFragment(), View.OnClickListener {
    private var reqCode = 1001


    //  params={access_token=d963fbe9bcb16cba15111be3b736e5eb,
    //  session_token=01660f3c2ad3dd860da5cadffefc0b21,
    //  image0.jpg=/storage/emulated/0/Android/data/com.mockuai.mkselleros/cache/luban_disk_cache/1587472235348612.jpg,
    //  app_key=494f333624df2efdc0ded421c616e413,
    //  format=json,
    //  api_sign=028b9c04cce858e3738a55abdacd7acb,
    //  server_version=10,
    //  version=2.30.1,
    //  phone_model=VOG-AL00}

    private val uploadUrl = "https://apidaily.mockuai.com/ec/c/message/file/image/upload"

    private val uploadFileMediaType = "image/jpeg"

    private val loginUrl = "https://wanandroid.com/user/login"
    private val loginUserName = "AqrAirSigns"
    private val loginPassword = "Aqrwanandroid520"

    private val downloadApk = "https://download.mockuai.com/app/wdseller.apk"

    private val testUrl =
        "https://act.mockuai.com/data/prodwudizhanggui.json?session_token=c47e32f4ac60b27f5a55e1fdf1834df5&app_key=5a65c97e356672d4af0f47ae38e83196&format=json&api_sign=eca0d2862484608338961830d2607ae7&server_version=10&version=1.11.0&timestamp=1584943757030&phone_model=VOG-AL00"
    private val httpCallback: IHttpCallback<BaseResp<UpdateEntity?>> =
        object : OkHttpRequest.SimpleHttpCallback<BaseResp<UpdateEntity?>>() {
            override fun onFinish(data: BaseResp<UpdateEntity?>?, e: Throwable?) {
                super.onFinish(data, e)
                data?.let {
                    ToastHelper.shortShow(" onFinish \n code : ${it.code} \n msg : ${it.msg} \n data : ${it.data?.android?.string()}")
                    LogHelper.d("AqrLei", "data : ${it.data?.android?.string()}")
                }
                e?.let {
                    ToastHelper.shortShow(" onFinish \n error : ${it.message}")
                    LogHelper.d("AqrLei", "onFinish - error : ${it.message}")
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_net, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvGet.setOnClickListener(this)
        tvPost.setOnClickListener(this)
        tvDownload.setOnClickListener(this)
        tvUpload.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvGet -> {
                val type = Types.newParameterizedType(
                    BaseResp::class.java,
                    UpdateEntity::class.java)
                get(httpCallback, type)
            }
            R.id.tvPost -> {
                OkHttpHelper.postForm(
                    loginUrl,
                    LoginResponse::class.java,
                    mutableMapOf<String, String>().apply {
                        put("username", loginUserName)
                        put("password", loginPassword)
                    },
                    object : OkHttpRequest.SimpleHttpCallback<LoginResponse>() {
                        override fun onFinish(data: LoginResponse?, e: Throwable?) {
                            super.onFinish(data, e)
                            ToastHelper.shortShow("id : ${data?.data?.id}, username : ${data?.data?.username}")
                        }
                    }
                )
            }
            R.id.tvDownload -> {
                download()
            }
            R.id.tvUpload -> {
                CacheFileUtil.pickFileItem(null, this, reqCode, "image/*")
            }
        }
    }

    private fun upload(file: File?) {
        file ?: return
        OkHttpHelper.postFile(uploadUrl, Any::class.java,
            mutableMapOf<String, Any>().apply {
                put("image0.jpg", file)
                put("access_token", "c3486ca1c85ff885b73bb1939d8c9df6")
                put("session_token", "47b8134ffb4f48a271cd1a9bc654037c")
                put("app_key", "494f333624df2efdc0ded421c616e413")
                put("format", "json")
                put("api_sign", "b57c8b0036a9c08745155bce0a1d35d6")
                put("server_version", "10")
                put("version", "2.30.1")
                put("phone_model", "VOG-AL00")
            }, object : OkHttpRequest.SimpleHttpCallback<Any?>() {})
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == reqCode && resultCode == Activity.RESULT_OK) {
            context?.let {
                data?.data?.let { uri ->
                    val dir = CacheFileUtil.getAppFilesDirFile(it, "images")
                    val file = File.createTempFile("test", ".jpg", dir)
                    coroutineRun(backgroundBlock = {
                        CacheFileUtil.writeFileFromUri(it, file, uri)
                    }, resultCallback = { result ->
                        LogHelper.d("AqrLei", "file : ${result?.absolutePath ?: "empty"}")
                        upload(result)
                    })
                }

                LogHelper.d("AqrLei", "${data?.data}")
                    ImageHelper.getBitmapFromUri(it, data?.data) { bitmap ->
                    llBg.background = BitmapDrawable(bitmap)
                }
            }
        }
    }


    private fun <T> get(callback: IHttpCallback<T>, rawType: Type) =
        OkHttpHelper.get(testUrl, rawType, callback)

    private fun download() {

        context?.let {
            val callback = object : OkHttpRequest.FileCallback() {
                override fun onFinish(data: File?, e: Throwable?) {
                    super.onFinish(data, e)
                    data?.let {
                        ToastHelper.shortShow(" onFinish \n filePath : ${it.absolutePath} \n}")
                        LogHelper.d("AqrLei", " onFinish \n filePath : ${it.absolutePath} \n}")
                    }
                    e?.let {
                        ToastHelper.shortShow(" onFinish \n error : ${it.message}")
                        LogHelper.d("AqrLei", "onFinish - error : ${it.message}")
                    }
                }
            }

            val transformer = FileTransformer("qexdTest", ".apk", CacheFileUtil.getAppFilesDirFile(it, "apk"), callback)

            OkHttpHelper.download(downloadApk, File::class.java, callback, transformer)
        }
    }
}