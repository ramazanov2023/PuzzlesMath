package com.example.puzzlesmath.ui.board

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import java.util.Random
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.example.puzzlesmath.R

class PuzzlesView(context: Context,attrs:AttributeSet):View(context,attrs) {
    private var sizePuzzle = 0F
    private var rowPuzzle = 5
    private var colPuzzle = 4
    private var puzzles = Puzzles()
    private var newAnim: ValueAnimator = ValueAnimator()

    private val puzzle = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLUE
        strokeWidth = 4F
    }

    private val task = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLUE
        textSize = 36F
    }

    init {
        setAnimation()
    }

    private fun setAnimation() {
        val middleColor = resources.getColor(R.color.purple_200)
        val endColor = resources.getColor(R.color.black)
//        newAnim = ValueAnimator.ofArgb(middleColor, endColor)
        newAnim = ValueAnimator.ofInt()
        newAnim = ValueAnimator.ofFloat()
        newAnim.duration = 200
        newAnim.addUpdateListener {
//            boardSelectCell.color = it.animatedValue as Int
            postInvalidateOnAnimation()
        }
    }


    fun setPuzzles(puzzles:Puzzles){
        this.puzzles = puzzles
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec);
        val widthSize = MeasureSpec.getSize(widthMeasureSpec);
        val heightMode = MeasureSpec.getMode(heightMeasureSpec);
        val heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.e("gggg","1  - widthMeasureSpec-$widthMeasureSpec  heightMeasureSpec-$heightMeasureSpec")
        Log.e("gggg","2  - width-$width  height-$height")
        Log.e("gggg","3  - widthMode-$widthMode  widthSize-$widthSize")
        Log.e("gggg","4  - heightMode-$heightMode  heightSize-$heightSize")

        setMeasuredDimension(widthSize, (widthSize / 4) + widthSize)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        sizePuzzle = (width/4).toFloat()
        Log.e("gggg","3  - width-$width  height-$height")
        drawPuzzles(canvas)
    }

    private fun drawPuzzles(canvas: Canvas?) {
        puzzles.puzzlesList.forEach {
            val col = it.col
            val row = it.row
            puzzle.color = it.color
            task.color = it.colorText
            val rect = Rect()
            task.getTextBounds(sizePuzzle.toString(),0,it.task.length,rect)
            val titleWidth = task.measureText(it.task)
            val titleHeight = rect.height()
            canvas?.drawRoundRect(col*sizePuzzle,row*sizePuzzle,(col+1)*sizePuzzle,(row+1)*sizePuzzle,30F,30F,puzzle)
            canvas?.drawText(it.task,col*sizePuzzle+(sizePuzzle/2)-(titleWidth/2),row*sizePuzzle+(sizePuzzle/2)+(titleHeight/2),task)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if(event?.action == MotionEvent.ACTION_DOWN){
            val row = (event.y / sizePuzzle).toInt()
            val col = (event.x / sizePuzzle).toInt()
            puzzles.indexPuzzle = row*colPuzzle + col
            Log.e("cccc","0  - row-$row  col-$col  puzzles.indexPuzzle-${puzzles.indexPuzzle}")
            true
        }else{
            false
        }
    }

    /*private fun longModeSet() {
        val titleSmall =
            ObjectAnimator.ofFloat(binding.classicStartModeFirstName, "textSize", 34F, 46f).apply {
                duration = 400
                interpolator = DecelerateInterpolator()
            }
        val short = ObjectAnimator.ofFloat(binding.classicTopFrame, "scaleY", 1f).apply {
            duration = 260
            interpolator = DecelerateInterpolator()
            // для scale необходимо установить pivot(в атрибутах или здесь,в коде)
        }
        val fadeLastNameMode =
            ObjectAnimator.ofFloat(binding.classicStartModeLastName, "alpha", 1f).apply {
                duration = 300
                interpolator = DecelerateInterpolator()
            }
        val fadeDescriptionMode =
            ObjectAnimator.ofFloat(binding.classicModeDescription, "alpha", 1f).apply {
                duration = 300
                interpolator = DecelerateInterpolator()
            }
        AnimatorSet().apply {
            play(short).with(titleSmall).with(fadeLastNameMode).with(fadeDescriptionMode)
            start()
        }
    }*/

}