package com.example.tbcacademyhomework.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentGameBinding
import kotlin.math.sqrt

private const val ARG_COUNT = "itemCount"

class GameFragment : Fragment() {
    private var count: Int = 0
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val gameManager by lazy { GameManager() }
    private val gameAdapter by lazy { GameAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            count = it.getInt(ARG_COUNT, 9)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initListeners()
        startGame()

    }

    private fun startGame() {
        gameManager.setCellCount(count)
    }

    private fun initListeners() {
        gameAdapter.onItemClick {
            gameManager.selectCell(it)
        }

        gameManager.onDataUpdateListener {
            println(gameManager.getCells())
            gameAdapter.submitList(gameManager.getCells())
        }

        gameManager.onGameEndListener {

            binding.rvGameBoard.isEnabled = false
            binding.restartButton.isVisible = true

            if (it == null) {
                binding.tvStatus.text = getString(R.string.tie)
            } else {
                binding.tvStatus.text = getString(R.string.winner, it.name)
            }
        }

        binding.restartButton.setOnClickListener {
            gameManager.restartGame()
            binding.restartButton.isVisible = false
            binding.tvStatus.text = ""
        }
    }

    private fun initRecycler() {
        val gridCount = sqrt(count.toDouble()).toInt()
        binding.rvGameBoard.layoutManager = GridLayoutManager(requireContext(), gridCount)
        binding.rvGameBoard.adapter = gameAdapter

    }

    companion object {
        fun newInstance(param1: Int) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COUNT, param1)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}