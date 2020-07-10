# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



##---------------Begin: proguard configuration for 阿里云推送 ----------
-keepclasseswithmembernames class ** {
    native <methods>;
}
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-keep class com.ut.** {*;}
-keep class com.ta.** {*;}
-keep class anet.**{*;}
-keep class anetwork.**{*;}
-keep class org.android.spdy.**{*;}
-keep class org.android.agoo.**{*;}
-keep class android.os.**{*;}
-keep class org.json.**{*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-dontwarn anet.**
-dontwarn org.android.spdy.**
-dontwarn org.android.agoo.**
-dontwarn anetwork.**
-dontwarn com.ut.**
-dontwarn com.ta.**
# 小米通道
-keep class com.xiaomi.** {*;}
-dontwarn com.xiaomi.**
# 华为通道
-keep class com.huawei.** {*;}
-dontwarn com.huawei.**
# GCM/FCM通道
-keep class com.google.firebase.**{*;}
-dontwarn com.google.firebase.**
# OPPO通道
-keep public class * extends android.app.Service
# VIVO通道
-keep class com.vivo.** {*;}
-dontwarn com.vivo.**
# 魅族通道
-keep class com.meizu.cloud.** {*;}
-dontwarn com.meizu.cloud.**
##---------------End: proguard configuration for 阿里云推送 ----------

##---------------Begin: proguard configuration for 阿里云崩溃分析  ----------
#keep crashreporter
-keep class com.alibaba.motu.crashreporter.MotuCrashReporter{ *;}
-keep class com.alibaba.motu.crashreporter.ReporterConfigure{*;}
-keep class com.alibaba.motu.crashreporter.utrestapi.UTRestReq{*;}
-keep interface com.alibaba.motu.crashreporter.IUTCrashCaughtListener{*;}
-keep interface com.alibaba.motu.crashreporter.ICrashReportSendListener{*;}
-keep interface com.alibaba.motu.crashreporter.ICrashReportDataListener{*;}
-keep interface com.ut.mini.crashhandler.*{*;}
-keep class com.uc.crashsdk.**{*;}
-keep class com.alibaba.motu.crashreporter.YouKuCrashReporter{public *;}
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod

##---------------End: proguard configuration for 阿里云崩溃分析 ----------


##---------------Begin: proguard configuration for 阿里云性能分析  ----------
-keep class com.taobao.monitor.APMLauncher{*;}
-keep class com.taobao.monitor.impl.logger.Logger{*;}
-keep class com.taobao.monitor.impl.logger.IDataLogger{*;}
-keep class com.taobao.monitor.impl.data.AbsWebView{*;}
-keep class com.taobao.monitor.impl.data.GlobalStats{*;}
-keep class com.taobao.monitor.impl.common.Global{*;}
-keep class com.taobao.monitor.impl.data.WebViewProxy{*;}
-keep class com.taobao.monitor.impl.logger.Logger{*;}
-keep class com.taobao.monitor.impl.processor.pageload.IProcedureManager{*;}
-keep class com.taobao.monitor.impl.processor.pageload.ProcedureManagerSetter{*;}
-keep class com.taobao.monitor.impl.util.TimeUtils{*;}
-keep class com.taobao.monitor.impl.util.TopicUtils{*;}
-keep class com.taobao.monitor.impl.common.DynamicConstants{*;}
-keep class com.taobao.application.common.data.DeviceHelper{*;}
-keep class com.taobao.application.common.impl.AppPreferencesImpl{*;}
-keep class com.taobao.monitor.impl.processor.launcher.PageList{*;}
-keep class com.taobao.monitor.impl.processor.fragmentload.FragmentInterceptorProxy{*;}
-keep class com.taobao.monitor.impl.processor.fragmentload.IFragmentInterceptor{*;}
-keep class com.taobao.monitor.impl.logger.DataLoggerUtils{*;}
-keep interface com.taobao.monitor.impl.data.IWebView{*;}
-keep interface com.taobao.monitor.impl.processor.IProcessor{*;}
-keep interface com.taobao.monitor.impl.processor.IProcessorFactory{*;}
-keep interface com.taobao.monitor.impl.logger.IDataLogger{*;}
-keep interface com.taobao.monitor.impl.trace.IDispatcher{*;}
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
##---------------End: proguard configuration for 阿里云性能分析 ----------


##---------------Begin: proguard configuration for 阿里云远程日志  ----------
#keep class
-keep interface com.taobao.tao.log.ITLogController{*;}
-keep class com.taobao.tao.log.upload.*{*;}
-keep class com.taobao.tao.log.message.*{*;}
-keep class com.taobao.tao.log.LogLevel{*;}
-keep class com.taobao.tao.log.TLog{*;}
-keep class com.taobao.tao.log.TLogConstant{*;}
-keep class com.taobao.tao.log.TLogController{*;}
-keep class com.taobao.tao.log.TLogInitializer{public *;}
-keep class com.taobao.tao.log.TLogUtils{public *;}
-keep class com.taobao.tao.log.TLogNative{*;}
-keep class com.taobao.tao.log.TLogNative$*{*;}
-keep class com.taobao.tao.log.CommandDataCenter{*;}
-keep class com.taobao.tao.log.task.PullTask{*;}
-keep class com.taobao.tao.log.task.UploadFileTask{*;}
-keep class com.taobao.tao.log.upload.LogFileUploadManager{public *;}
-keep class com.taobao.tao.log.monitor.**{*;}
#兼容godeye
-keep class com.taobao.tao.log.godeye.core.module.*{*;}
-keep class com.taobao.tao.log.godeye.GodeyeInitializer{*;}
-keep class com.taobao.tao.log.godeye.GodeyeConfig{*;}
-keep class com.taobao.tao.log.godeye.core.control.Godeye{*;}
-keep interface com.taobao.tao.log.godeye.core.GodEyeAppListener{*;}
-keep interface com.taobao.tao.log.godeye.core.GodEyeReponse{*;}
-keep interface com.taobao.tao.log.godeye.api.file.FileUploadListener{*;}
-keep public class * extends com.taobao.android.tlog.protocol.model.request.base.FileInfo{*;}
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
##---------------End: proguard configuration for 阿里云远程日志 ----------


##---------------Begin: proguard configuration for 阿里云移动数据分析 ----------
-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}
##---------------End: proguard configuration for 阿里云移动数据分析 ----------


##---------------Begin: proguard configuration for 阿里云hotfix ----------
-keep class com.taobao.sophix.**{*;}
-keep class com.ta.utdid2.device.**{*;}
-dontwarn com.alibaba.sdk.android.utils.**
#防止inline
-dontoptimize
##---------------End: proguard configuration for 阿里云hotfix ----------

##---------------Begin: proguard configuration for 阿里云移动用户反馈 ----------
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-keep class com.ut.** {*;}
-dontwarn com.ut.**
-keep class com.ta.** {*;}
-dontwarn com.ta.**
##---------------End: proguard configuration for 阿里云移动用户反馈 ----------
