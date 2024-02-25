package com.example.puzzlesmath.ui.game

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.puzzlesmath.R
import com.example.puzzlesmath.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var xBitmap: Bitmap
    private val viewModel by viewModels<GameViewModel> { GameViewModelFactory() }
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater)


//        binding.puzzleBoard.setPuzzles(viewModel.puzzles)

        val width = requireActivity().windowManager.defaultDisplay.width

        xBitmap = BitmapFactory.decodeResource(resources, R.drawable.fot_11)
        viewModel.puzzles.setBitmap(xBitmap,width)
        viewModel.puzzles.getPuzzleTasks()

        binding.puzzleRecycler.adapter = GameAdapter()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        /*viewModel.puzzles.puzzlesLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.puzzles.setPuzzleList(it)
        })*/

        binding.puzzlesBtn.setOnClickListener {
            viewModel.puzzles.getPuzzleTasks()
        }


        return binding.root
    }
}