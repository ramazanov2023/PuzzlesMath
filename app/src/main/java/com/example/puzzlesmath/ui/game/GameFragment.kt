package com.example.puzzlesmath.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.puzzlesmath.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private val viewModel by viewModels<GameViewModel> { GameViewModelFactory() }
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.puzzleBoard.setPuzzles(viewModel.puzzles)

        viewModel.puzzles.puzzlesLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.puzzles.setPuzzleList(it)
        })



        return binding.root
    }
}