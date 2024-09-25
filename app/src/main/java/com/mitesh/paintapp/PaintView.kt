package com.mitesh.paintapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var path = Path()
    private var drawColor = Color.BLACK
    private var strokeWidth = 10f
    private var eraseMode = false
    private var paths = mutableListOf<Pair<Path, Paint>>()

    private val paint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    init {
        paint.color = drawColor
        paint.strokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw all previous paths
        for ((p, paint) in paths) {
            canvas.drawPath(p, paint)
        }

        // Draw the current path
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                // Store the path and paint
                paths.add(Pair(Path(path), Paint(paint)))
                path.reset()
            }
        }
        invalidate()
        return true
    }

    // Change draw color
    fun setColor(newColor: Int) {
        drawColor = newColor
        paint.color = newColor
    }

    // Change stroke width
    fun setStrokeWidth(newWidth: Float) {
        strokeWidth = newWidth
        paint.strokeWidth = newWidth
    }

    // Enable erase mode
    fun enableEraser() {
        eraseMode = true
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        paint.strokeWidth = 50f // Set the width for erasing
    }

    // Disable erase mode
    fun disableEraser() {
        eraseMode = false
        paint.xfermode = null
    }

    // Clear the canvas
    fun clearCanvas() {
        paths.clear()
        invalidate()
    }
}










//
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Color
//import android.graphics.Paint
//import android.graphics.Path
//import android.graphics.PorterDuff
//import android.graphics.PorterDuffXfermode
//import android.util.AttributeSet
//import android.view.MotionEvent
//import android.view.View
//import android.view.ViewGroup
//import com.mitesh.paintapp.MainActivity.Companion.paint
//import com.mitesh.paintapp.MainActivity.Companion.path
//import kotlin.io.path.Path
//
//class PaintView:View{
//
//    private var eraseMode = false
//    private var pathList = ArrayList<Path>()
//    private var drawColor = Color.BLACK
//    private var strokeWidth = 10f
//    var params:ViewGroup.LayoutParams?= null
//
//
//    constructor(context: Context) : this(context, null){
//        init()
//
//    }
//    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
//        init()
//
//    }
//
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        init()
//    }
//
//    private fun init(){
//        paint.isAntiAlias = true
//        paint.style = Paint.Style.STROKE
//        paint.color = Color.BLACK
//        paint.strokeWidth = strokeWidth
//        paint.strokeJoin = Paint.Join.ROUND
//        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT )
//    }
//
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        for(i in pathList.indices){
//            paint.color = Color.BLACK
//            canvas.drawPath(pathList[i],paint)
//            invalidate()
//        }
//    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        val x = event.x
//        val y = event.y
//
//        when(event.action){
//            MotionEvent.ACTION_DOWN->{
//                path.moveTo(x,y)
//            }
//            MotionEvent.ACTION_MOVE->{
//                path.lineTo(x,y)
//            }
//            else-> return false
//        }
//        postInvalidate()
//        return true
//    }
//
//    fun setColor(newColor: Int) {
//        drawColor = newColor
//        paint.color = newColor
//    }
//
//    fun setStrokeWidth(newWidth: Float) {
//        strokeWidth = newWidth
//        paint.strokeWidth = newWidth
//    }
//
//    // Enable erase mode
//    fun enableEraser() {
//        eraseMode = true
//        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
//        paint.strokeWidth = 50f // Set the width for erasing
//    }
//
//    // Disable erase mode
//    fun disableEraser() {
//        eraseMode = false
//        paint.xfermode = null
//    }
//
//
//
//}