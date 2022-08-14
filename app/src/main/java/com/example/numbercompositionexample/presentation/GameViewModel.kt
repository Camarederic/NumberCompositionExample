package com.example.numbercompositionexample.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numbercompositionexample.R
import com.example.numbercompositionexample.data.GameRepositoryImpl
import com.example.numbercompositionexample.domain.entity.GameResult
import com.example.numbercompositionexample.domain.entity.GameSettings
import com.example.numbercompositionexample.domain.entity.Level
import com.example.numbercompositionexample.domain.entity.Question
import com.example.numbercompositionexample.domain.usecases.GenerateQuestionUseCase
import com.example.numbercompositionexample.domain.usecases.GetGameSettingsUseCase

// 66) Создаем GameViewModel
class GameViewModel(application: Application) :
    AndroidViewModel(application) { // 101) Меняем ViewModel на AndroidViewModel и добавляем параметр application: Application

    // 69) Создаем две переменные
    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level

    // 67) Создаем репозиторий
    private val repository = GameRepositoryImpl

    // 102) Добавляем контекст
    private val context = application

    // 68) Создаем ссылки на наши кейсы
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    // 83) Чтобы можно было работать с методом onCleared выносим в переменную
    private var timer: CountDownTimer? = null

    // 75) Создаем переменные для форматированного времени
    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    // 87) Создаем объект LiveData чтобы можно было отобразить вопросы
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    // 95) Создаем объект LiveData для отображения процента правильных ответов
    private val _percentOfRightQuestions = MutableLiveData<Int>()
    val percentOfRightQuestions: LiveData<Int>
        get() = _percentOfRightQuestions

    // 96) Создаем объект LiveData для отображения прогресса по првильным ответам
    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    // 105) Создаем объект LiveData для отображения достаточного кол-ва правильных ответов
    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswers: LiveData<Boolean>
        get() = _enoughCountOfRightAnswers

    // 106) Создаем объект LiveData для отображения достаточного кол-ва процентов правильных ответов
    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    // 108) Создаем объект LiveData для отображения secondary progress
    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    // 111) Создаем объект LiveData для отображения результата игры
    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    // 93) Создаем две переменные для кол-ва правильных ответов и для всех вопросов
    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    // 70) Создаем метод для начала игры
    fun startGame(level: Level) {
        getGameSettings(level)
        // 81) Вызываем метод
        startTimer()
        // 89) Вызываем метод
        generateQuestion()
    }

    // 109) Создаем метод для настроек игры
    private fun getGameSettings(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase(level)
        // 110)
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    // 90) Создаем метод для выбора вопросов
    fun chooseAnswer(number: Int) {
        // 92) Получаем значение правильного вопроса
        val rightAnswer = question.value?.rightAnswer
        // 94) Делаем проверку
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
        // 104) Вызываем метод
        updateProgress()

        generateQuestion()
    }

    // 97) Создаем метод для обновления прогресса по вопросам
    private fun updateProgress() {
        // 99)
        val percent = calculatePercentOfRightAnswers()
        // 100) Устанавливаем значение в объект
        _percentOfRightQuestions.value = percent
        // 103) При обновлении прогресса получаем строку
        _progressAnswers.value = String.format(
            context.resources.getString(R.string.textViewAnswersProgress),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )
        // 107) Придаем значение
        _enoughCountOfRightAnswers.value =
            countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _enoughPercentOfRightAnswers.value = percent >= gameSettings.minPercentOfRightAnswers
    }

    // 98) Создаем метод для вычисления правильных ответов в процентах
    private fun calculatePercentOfRightAnswers(): Int {
        return ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    // 71) Создаем метод для таймера
    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(millisUnitFinished: Long) {
                // 80) Кладем новое значение
                _formattedTime.value = formatTime(millisUnitFinished)
            }

            override fun onFinish() {
                // 74) Вызываем метод
                finishGame()
            }

        }
        // 84) Стартуем
        timer?.start()
    }

    // 86) Генерируем след.вопрос
    private fun generateQuestion() {
        // 88) Вызываем usecase
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }

    // 76) Создаем метод, который будет форматировать время
    private fun formatTime(millisUnitFinished: Long): String {
        // 77) Получаем общее кол-во секунд
        val seconds = millisUnitFinished / MILLIS_IN_SECONDS
        // 78) Получаем сколько это минут
        val minutes = seconds / SECONDS_IN_MINUTES
        // 79) Получаем оставшееся кол-во секунд
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    // 73) Создаем метод окончания игры
    private fun finishGame() {
        // 112)
         _gameResult.value = GameResult(
            winner = enoughCountOfRightAnswers.value == true && enoughPercentOfRightAnswers.value == true,
            countOfRightAnswers = countOfRightAnswers,
            countOfQuestions = countOfQuestions,
            gameSettings = gameSettings
        )
    }

    // 82) Когда уходим из фрагмента нужно чтобы таймер отменялся в этом методе
    override fun onCleared() {
        super.onCleared()
        // 85) Останавливаем таймер
        timer?.cancel()
    }

    // 72) Создаем константу
    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
    }
}