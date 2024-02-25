package com.example.puzzlesmath.ui.board

import android.content.Context
import android.graphics.*
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.puzzlesmath.R

class TestBitmapView(context:Context,attrs:AttributeSet):View(context, attrs) {

    private var rectSrc: Rect
    private var rectDst: Rect

    // BitmapFactory.decodeResource позволяет создавать Bitmap из картинки в папке drawable
    private var xBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.fot_11)
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var xMatrix = Matrix().apply {
        postRotate(45F)
        postScale(2F, 3F)
        postTranslate(200F, 50F)
    }

    // Этот класс позволяет масштабировать,перемещать и поворачивать Bitmap
    private var mat = Matrix().apply {
        postScale(0.3F, 0.3F)
        postTranslate(100F, 100F)
    }

    init {

        val info =
        String.format("Info: size = %s x %s, bytes = %s (%s), config = %s",
            xBitmap.width,
            xBitmap.getHeight(),
            xBitmap.byteCount,
            xBitmap.getRowBytes(),
            xBitmap.getConfig())
        Log.d("log", info)

        val xWidth = xBitmap.getWidth() / 2
        val xHeight = xBitmap.getHeight() / 2
        rectSrc = Rect(0, 0, xWidth, xHeight)
        rectDst = Rect(100, 800, 100+xWidth/2, 800+xHeight/2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawARGB(80, 102, 204, 255)
//        canvas?.drawBitmap(xBitmap, 50F, 50F, paint)
//        canvas?.drawBitmap(xBitmap, mat, paint)
        // Вырезаем прямоугольник из bitmap(картинки) и преобразуем его в другой прямоуголник(подстраивая картинку под его размеры и расположение)
//        canvas?.drawBitmap(xBitmap, rectSrc, rectDst, paint)
        createImagePart(canvas)
    }

    private fun createImagePart2(canvas: Canvas?){
        val newBitmap = Bitmap.createScaledBitmap(xBitmap,width,width,false)
        val newWidth = newBitmap.width / 6
//        val part1 = Bitmap.createBitmap(xBitmap,newWidth*2,newWidth*3,newWidth,newWidth)
//        canvas?.drawBitmap(newBitmap,0F,700F,Paint())
        var hor = 0
        for(i in 0 .. 35){
            if(i % 6 == 0) hor = 0
            val ver = i / 6
            val part = Bitmap.createBitmap(newBitmap,newWidth*hor,newWidth*ver,newWidth,newWidth)
            canvas?.drawBitmap(part,newWidth*hor+80F*hor,newWidth*ver+80F*ver,Paint())
            hor++
        }

    }

    private fun createImagePart(canvas: Canvas?){
        val newBitmap = Bitmap.createScaledBitmap(xBitmap,width,width,false)
        val newWidth = newBitmap.width / 6
//        val part1 = Bitmap.createBitmap(xBitmap,newWidth*2,newWidth*3,newWidth,newWidth)
//        canvas?.drawBitmap(newBitmap,0F,700F,Paint())
        var hor = 0
        for(i in 0 .. 35){
            if(i % 6 == 0) hor = 0
            val ver = i / 6

//            val round = RoundRectShape()
            val roundRect = Rect(newWidth*hor,newWidth*ver,newWidth*(hor+1),newWidth*ver)

            val part = Bitmap.createBitmap(newBitmap,newWidth*hor,newWidth*ver,newWidth,newWidth)

            canvas?.drawBitmap(part,0F,0F,Paint())
            hor++
        }

    }

}