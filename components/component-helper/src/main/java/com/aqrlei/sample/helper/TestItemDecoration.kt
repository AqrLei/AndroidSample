package com.aqrlei.sample.helper

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aqrlei.helper.log.LogHelper

/**
 * created by AqrLei on 2020/7/3
 */
class TestItemDecoration( private val callback:(position:Int) -> Boolean):RecyclerView.ItemDecoration(){

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.YELLOW
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.textSize = 54F
        it.color = Color.BLACK
    }
    private var labelHeight = 54

    private val textRect = Rect()
    private val text = "两周前的消息"
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        Log.d("AqrLei","test onDrawOver")
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        val itemCount = parent.adapter?.itemCount?:0
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
         for (i in 0 until childCount){
             val view = parent.getChildAt(i)
             val position = parent.getChildAdapterPosition(view)
             val bool = callback(position)
             val text = "历史消息"

             if(bool){
                 c.drawRect(left.toFloat(),view.top.toFloat() - labelHeight,right.toFloat(),view.top.toFloat(),paint)


                 c.drawText(text,left + right/2F,view.top - labelHeight/2F,textPaint)
             }
         }
        Log.d("AqrLei","Test onDraw ")
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val bool = callback(position)
        textPaint.getTextBounds(text,0,text.length,textRect)
        labelHeight = textRect.height() + 54
        outRect.top = if (bool) labelHeight else 0
        Log.d("AqrLei","Test offset ")
    }
}