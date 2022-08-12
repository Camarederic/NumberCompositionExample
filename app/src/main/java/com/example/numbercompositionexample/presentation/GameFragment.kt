package com.example.numbercompositionexample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numbercompositionexample.R
import com.example.numbercompositionexample.databinding.FragmentGameBinding
import com.example.numbercompositionexample.domain.entity.GameResult
import com.example.numbercompositionexample.domain.entity.GameSettings
import java.lang.RuntimeException
import com.example.numbercompositionexample.domain.entity.Level

// 21) Создаем фрагмент
class GameFragment : Fragment() {

    // 44) Создаем переменную, которая будет хранить уровень
    private lateinit var level: Level

    // 35) Создаем binding
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    // 45) создаем метод onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 46) Вызываем метод
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 36) Переопределяем метод
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 55) Устанавливаем клик слушателя на первый вариант ответа
        binding.textViewOption1.setOnClickListener {
            launchGameFinishedFragment(
                GameResult(
                    true,
                    0,
                    0,
                    GameSettings(0,0,0,0)
                ))
        }


    }

    // 37) Переопределяем метод
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // 43) Создаем метод гду будем получать объект из аргументов
    private fun parseArgs() {
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    // 54) Создаем метод, который будет запускать экран окончания игры
    private fun launchGameFinishedFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer,GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    // 41) Создаем метод для перехода в игру
    companion object {

        // 61) Создаем константу
        const val NAME = "GameFragment"

        // 42) Создаем константу
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }

}