package com.aqrlei.sample

import android.app.Application
import com.aqrlei.helper.init.ContextInitHelper
import com.aqrlei.helper.log.LogHelper
import com.aqrlei.helper.log.LogLevel
import com.aqrlei.helper.log.config.LogConfig
import com.aqrlei.helper.log.config.SimpleLogPrinterConfig
import com.aqrlei.helper.log.printer.ConsoleLogPrinter
/**
 * Created by AqrLei on 2019-09-23
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ContextInitHelper.doInit(this)
        LogHelper.initAppModule("app").config(LogConfig.create(this.applicationContext).also {
            it.globalTag = "AndroidSample"
            it.level = LogLevel.VERBOSE
        }).setLogPrinters(ConsoleLogPrinter(SimpleLogPrinterConfig.create().also {
            it.level = LogLevel.DEBUG
        }))
        InitHelper.init(this)
    }
}