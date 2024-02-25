package com.example.puzzlesmath.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzlesmath.databinding.ItemPuzzleBinding
import com.example.puzzlesmath.ui.board.Task

class GameAdapter:ListAdapter<Task,GameAdapter.Holder>(Diff) {
    class Holder(private val binding:ItemPuzzleBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.task = task
            binding.executePendingBindings()
        }
    }
    companion object Diff:DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemPuzzleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}