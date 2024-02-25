package com.example.puzzlesmath.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.puzzlesmath.ui.board.Puzzles

class GameViewModel : ViewModel() {

    val puzzles = Puzzles()

}