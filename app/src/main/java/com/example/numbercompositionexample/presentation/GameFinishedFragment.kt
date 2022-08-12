package com.example.numbercompositionexample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.example.numbercompositionexample.R
import com.example.numbercompositionexample.databinding.FragmentGameFinishedBinding
import com.example.numbercompositionexample.domain.entity.GameResult
import java.lang.RuntimeException

// 22) Создаем фрагмент
class GameFinishedFragment : Fragment() {

    // 50) Создаем переменную
    private lateinit var gameResult: GameResult

    // 32) Создаем binding
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    // 52) Создаем метод onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 53) Вызываем метод
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 33) Переопределяем метод
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 60) Добавление клика слушателя на кнопке назад
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })
        // 65) Создаем клик слушателя для кнопки
        binding.buttonGameAgain.setOnClickListener {
            retryGame()
        }

    }

    // 34) Переопределяем метод
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // 51) Создаем метод гду будем получать объект из аргументов
    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    // 59) Создаем метод для повтора игры
    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    // 49) Создаем метод
    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT,
                        gameResult) // 62) putSerializable меняем putParcelable
                }
            }
        }
    }

}