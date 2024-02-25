package com.example.puzzlesmath.ui.game

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzlesmath.ui.board.Task

@BindingAdapter("puzzleList")
fun setPuzzleList(recycler: RecyclerView, list: List<Task>?) {
    list?.let {
        Log.e("tttt", "1  -  list-${list.size}")
        (recycler.adapter as GameAdapter).submitList(list)
    }
}

@BindingAdapter("puzzleImage")
fun setPuzzleImage(imageView: ImageView, bitmap: Bitmap?) {
    bitmap?.let {
//        Log.e("tttt", "2  -  bitmap-${bitmap.height}")
        imageView.setImageBitmap(bitmap)
    }
}

@BindingAdapter("puzzleHide")
fun setPuzzleHide(textView: TextView, hide: Boolean?) {
    hide?.let {
//        Log.e("tttt", "6  -  hide-${hide}")
        if (hide) {
            textView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.VISIBLE
        }
    }
}