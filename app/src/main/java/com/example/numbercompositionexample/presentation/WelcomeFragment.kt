package com.example.numbercompositionexample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numbercompositionexample.R
import com.example.numbercompositionexample.databinding.FragmentWelcomeBinding
import java.lang.RuntimeException

// 19) Создаем фрагмент
class WelcomeFragment : Fragment() {  // вот такой подход принят в работе с фрагментами

    // 24) Создаем binding нулабельную для работы из фрагментов
    private var _binding: FragmentWelcomeBinding? = null
    // 26) Создаем ненулабельную переменную с геттером и с исключением
    private val binding: FragmentWelcomeBinding
    get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        avedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 23) Переопределяем метод
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 25) Создаем клик слушателя для кнопки
        binding.buttonUnderstand.setOnClickListener {
            // 40) Вызываем метод
            launchChooseLevelFragment()
        }
    }

    // 38) Создаем метод для запуска ChooseLevelFragment
    private fun launchChooseLevelFragment(){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, ChooseLevelFragment.newInstance())
            .addToBackStack(ChooseLevelFragment.NAME) // 57) Меняем null на ChooseLevelFragment.NAME
            .commit()
    }

    // 27) Создаем метод onDestroy
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}