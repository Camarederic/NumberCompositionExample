package com.example.numbercompositionexample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numbercompositionexample.R
import com.example.numbercompositionexample.databinding.FragmentGameBinding
import java.lang.RuntimeException

// 21) Создаем фрагмент
class GameFragment : Fragment() {

    // 35) Создаем binding
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    // 36) Переопределяем метод
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // 37) Переопределяем метод
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}