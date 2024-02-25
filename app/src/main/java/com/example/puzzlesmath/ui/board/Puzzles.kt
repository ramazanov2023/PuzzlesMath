package com.example.puzzlesmath.ui.board

import android.graphics.*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.puzzlesmath.R
import kotlin.random.Random

class Puzzles {
    private var width: Int = 0
    var indexPuzzle = -1
    var puzzlesList = listOf<Task>()
    private var xBitmap: Bitmap? = null

    private val _puzzlesLiveData = MutableLiveData<List<Task>>()
    val puzzlesLiveData: LiveData<List<Task>>
        get() = _puzzlesLiveData

    fun setPuzzleList(puzzlesList: List<Task>) {
        this.puzzlesList = puzzlesList
    }

    fun getPuzzleTasks() {
        val list = mutableListOf<Task>()
        list.addAll(multi(10))
        list.addAll(joinNumbers(10))
        list.shuffle()
        xBitmap ?:return
        Log.e("tttt","3  -  list-${list.size}")
        _puzzlesLiveData.value = convertToPuzzles(list)
    }

    private fun convertToPuzzles(list: List<Task>): List<Task> {
        var j = 0
        val xWidth = xBitmap!!.width
        val xHeight = xBitmap!!.height
        val frame = xHeight/5*4
        val out = (xWidth-frame)/2
        val clipBit = Bitmap.createBitmap(xBitmap!!,out,0,frame,xHeight)
        val xRatio = Math.round((xHeight.toFloat() / xWidth)*10)/10F
        val aRatio = (Math.round((xHeight.toFloat() / xWidth)*10)/10)*10
        Log.e("tttt","8  -  xRatio-${xRatio}")
        Log.e("tttt","9  -  aRatio-${aRatio}")
        Log.e("tttt","10  -  xWidth-${xWidth}  xHeight-${xHeight}")
        Log.e("tttt","12  -  frame-${frame}  out-${out}")
//        val newBitmap = Bitmap.createScaledBitmap(xBitmap!!,width,(width*xRatio).toInt(),false)
//        val newBitmap = Bitmap.createScaledBitmap(xBitmap!!,width,(width*10*aRatio)/10,false)
//        val newBitmap = Bitmap.createScaledBitmap(xBitmap!!,width,1350,false)
        val newBitmap = Bitmap.createScaledBitmap(clipBit,width,width/4*5,false)
        val newWidth = newBitmap.width / 4
        for(i in 0 until list.size) {
            val colors = shufflePuzzleColors()
            val hide = shufflePuzzleHide()
            if(i % 4 == 0) j = 0
            list[i].id = i
            list[i].col = j
            list[i].row = i / 4
            list[i].color = colors.first
            list[i].colorText = colors.second
            list[i].hide = hide
//            Log.e("tttt","5  -  newWidth-${newWidth}   i / 4-${i / 4}   newWidth*(i / 4)-${newWidth*(i / 4)}  newWidth*j-${newWidth*j}")
            list[i].bit = Bitmap.createBitmap(newBitmap,newWidth*j,newWidth*(i / 4),newWidth,newWidth)

            j++
        }
        Log.e("tttt","4  -  list-${list.size}")
        Log.e("tttt","11  -  width-${width}  height-${(width*12)/10}")
//        printTaskList(list)
        return list
    }


    private fun joinNumbers(count: Int): Array<Task> {
        val list = mutableListOf<Task>()
        for (i in 0 until count) {
            val a = Random.nextInt(9)
            val b = Random.nextInt(9)
            val answer = a + b
            val task = Task(task = "$a + $b", answer = answer)
            list.add(task)
        }
        return list.toTypedArray()
    }

    private fun multi(count: Int): Array<Task> {
        val list = mutableListOf<Task>()
        for (i in 0 until count) {
            val a = Random.nextInt(9)
            val b = Random.nextInt(9)
            val answer = a * b
            val task = Task(task = "$a x $b", answer = answer)
            list.add(task)
        }
        return list.toTypedArray()
    }


    fun printTaskList(list: List<Task>) {
        for (i in list) {
            Log.e("cccc","1  row-${i.row}  col-${i.col}  task-${i.task} answer-${i.answer} color-${i.color}")
        }
    }

    private fun shufflePuzzleColors():Pair<Int,Int>{
//        val colors = Random.nextInt(4)
        val colors = java.util.Random().nextInt(4)
        Log.e("gggg","5  - colors-$colors")
        return when(colors){
            1 -> {
                Pair(Color.GRAY,Color.YELLOW)
            }
            2 -> {
                Pair(Color.WHITE,Color.LTGRAY)
            }
            3 -> {
                Pair(Color.YELLOW,Color.GRAY)
            }
            else -> {
                Pair(Color.LTGRAY,Color.WHITE)
            }
        }
    }
    private fun shufflePuzzleHide():Boolean{
//        val colors = Random.nextInt(4)
        val hide = java.util.Random().nextInt(4)
        return when(hide){
            1 -> {
                false
            }
            2 -> {
                false
            }
            3 -> {
                true
            }
            else -> {
                false
            }
        }
    }

    fun setBitmap(xBitmap: Bitmap?, width: Int) {
        this.xBitmap = xBitmap
        this.width = width
    }
}

data class Task(
    var id:Int = 0,
    val task: String,
    val answer: Int,
    var bit: Bitmap? = null,
    var row: Int = -1,
    var col: Int = -1,
    var color: Int = 0,
    var colorText: Int = 0,
    var hide:Boolean = false
)