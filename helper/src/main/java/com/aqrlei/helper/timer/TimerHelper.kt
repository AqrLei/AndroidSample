package com.aqrlei.helper.timer

/**
 * created by AqrLei on 2020-01-04
 */
object  TimerHelper {

    fun getDefaultCountDownTimer(
        maxMillis:Long,
        onTimer:(Long)->Unit,
        onStart:(()->Unit)?=null,
        onComplete:(()->Unit)?=null):SimpleCustomTimer{
        return SimpleCustomTimer(maxMillis,object :SimpleCustomTimer.OnTimerListener {
            override fun onStart(countDown:Boolean) {
                onStart?.invoke()
            }

            override fun onNext(currentTime: Long, countDown: Boolean) {
                onTimer(currentTime)
            }
            override fun onComplete(countDown:Boolean) {
                onComplete?.invoke()
            }
        })
    }
}