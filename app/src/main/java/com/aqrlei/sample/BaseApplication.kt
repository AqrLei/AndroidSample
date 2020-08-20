package com.aqrlei.sample

import android.app.Application
import com.aqrlei.litelog.LogHelper
import com.aqrlei.litelog.LogLevel
import com.aqrlei.litelog.config.LogConfig
import com.aqrlei.litelog.config.SimpleLogPrinterConfig
import com.aqrlei.litelog.printer.ConsoleLogPrinter
import com.aqrlei.sample.base.ToastWrapper
import com.aqrlei.utilcollection.ContextInitUtil
import com.aqrlei.utilcollection.toast.ToastHelper

/**
 * Created by AqrLei on 2019-09-23
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ContextInitUtil.doInit(this)
        LogHelper.initAppModule("app").config(LogConfig.create(this.applicationContext).also {
            it.globalTag = "AndroidSample"
            it.level = LogLevel.VERBOSE
        }).setLogPrinters(ConsoleLogPrinter(SimpleLogPrinterConfig.create().also {
            it.level = LogLevel.DEBUG
        }))
        InitHelper.init(this)
        ToastHelper.initToast(ToastWrapper)
    }
}