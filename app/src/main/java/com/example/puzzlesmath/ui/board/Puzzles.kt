package com.example.puzzlesmath.ui.board

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class Puzzles {
    var indexPuzzle = -1
    var puzzlesList = listOf<Task>()

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
        _puzzlesLiveData.value = convertToPuzzles(list)
    }

    private fun convertToPuzzles(list: List<Task>): List<Task> {
        var j = 0
        for(i in 0 until list.size) {
            val colors = shufflePuzzleColors()
            if(i % 4 == 0) j = 0
            list[i].col = j++
            list[i].row = i / 4
            list[i].color = colors.first
            list[i].colorText = colors.second
        }
        printTaskList(list)
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
}

data class Task(
    val task: String,
    val answer: Int,
    var row: Int = -1,
    var col: Int = -1,
    var color: Int = 0,
    var colorText: Int = 0,
    var hide:Boolean = false
)