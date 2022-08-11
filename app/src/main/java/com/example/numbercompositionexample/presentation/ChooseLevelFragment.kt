package com.example.numbercompositionexample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numbercompositionexample.R
import com.example.numbercompositionexample.databinding.FragmentChooseLevelBinding
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

        }
        binding.buttonLevelEasy.setOnClickListener {

        }
        binding.buttonLevelMiddle.setOnClickListener {

        }
        binding.buttonLevelHard.setOnClickListener {

        }
    }

    // 31) Создаем метод onDestroy
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

