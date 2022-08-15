package com.example.numbercompositionexample.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
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

    // 113) Добавляем ссылку на ViewModel
    private lateinit var viewModel: GameViewModel

    // 118) Все textView с вариантами ответов добавляем в коллекцию при помощи ленивой инициализации
    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.textViewOption1)
            add(binding.textViewOption2)
            add(binding.textViewOption3)
            add(binding.textViewOption4)
            add(binding.textViewOption5)
            add(binding.textViewOption6)
        }
    }

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
        // 114) Присваиваем значение viewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireActivity().application))[GameViewModel::class.java]
        // 116) Вызываем метод
        observeViewModel()
        // 129) Вызываем метод
        setClickListenersToOptions()
        // 127) Стартуем игру
        viewModel.startGame(level)
//        // 55) Устанавливаем клик слушателя на первый вариант ответа
//        binding.textViewOption1.setOnClickListener {
//            launchGameFinishedFragment(
//                GameResult(
//                    true,
//                    0,
//                    0,
//                    GameSettings(0, 0, 0, 0)
//                ))
//        }
    }
    // 128) Добавляем клики слушателей на варианты ответов
    // Делаем это в отдельном методе и при помощи цикла for
    private fun setClickListenersToOptions(){
        for (tvOption in tvOptions){
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }


    // 115) Создаем метод
    private fun observeViewModel(){
        viewModel.question.observe(viewLifecycleOwner){
            // 117) Устнавливаем текст
            binding.textViewSum.text = it.sum.toString()
            binding.textViewLeftNumber.text = it.visibleNumber.toString()
            // 119) В цикле установим вварианты ответам
            for (i in 0 until tvOptions.size){
                tvOptions[i].text = it.answerOptions[i].toString()
            }
        }
        // 120) Устанавливаем полученный процент в прогрессбар
        viewModel.percentOfRightQuestions.observe(viewLifecycleOwner){
            binding.progressBar.setProgress(it, true)
        }
        // 121) Устанавливаем цвет для прогрессбара с ответами
        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner){
            val colorResId = if (it){
                android.R.color.holo_green_light
            }else {
                android.R.color.holo_red_light
            }
            val color = ContextCompat.getColor(requireActivity(), colorResId)
            binding.textViewAnswersProgress.setTextColor(color)
        }
        // 122) Также устанавливаем цвет для прогрессбара
        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner){
            val colorResId = if (it){
                android.R.color.holo_green_light
            }else{
                android.R.color.holo_red_light
            }
            val color= ContextCompat.getColor(requireActivity(), colorResId)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        // 123) Подписываемся на таймер
        viewModel.formattedTime.observe(viewLifecycleOwner){
            binding.textViewTimer.text = it
        }
        // 124) Подписываемся на минимальный процент
        viewModel.minPercent.observe(viewLifecycleOwner){
            binding.progressBar.secondaryProgress = it
        }
        // 125) Подписываемся на gameResult
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishedFragment(it)
        }
        // 130) Подписываемся на progressAnswers
        viewModel.progressAnswers.observe(viewLifecycleOwner){
            binding.textViewAnswersProgress.text = it
            // 131) Запускаем приложение
        }
    }

    // 37) Переопределяем метод
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // 43) Создаем метод гду будем получать объект из аргументов
    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    // 54) Создаем метод, который будет запускать экран окончания игры
    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, GameFinishedFragment.newInstance(gameResult))
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
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }

}