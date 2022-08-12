package com.example.numbercompositionexample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numbercompositionexample.R
import com.example.numbercompositionexample.databinding.FragmentChooseLevelBinding
import com.example.numbercompositionexample.domain.entity.Level
import java.lang.RuntimeException

// 20) Создаем фрагмент
class ChooseLevelFragment : Fragment() {

    // 28) Создаем binding
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 29) Переопределяем метод
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 30) Создаем клики слушателя для кнопок
        binding.buttonLevelTest.setOnClickListener {
            // 48) Вызываем метод для каждой кнопки
            launchGameFragment(Level.TEST)
        }
        binding.buttonLevelEasy.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.buttonLevelMiddle.setOnClickListener {
            launchGameFragment(Level.NORMAL)
        }
        binding.buttonLevelHard.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
    }


    // 31) Создаем метод onDestroy
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // 47) Создаем метод при нажатии на кнопку тестовый и входим в игру
    private fun launchGameFragment(level: Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    // 39) Создаем метод
    companion object {

        // 56) Создаем имя
        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment{
            return ChooseLevelFragment()
        }
    }
}

