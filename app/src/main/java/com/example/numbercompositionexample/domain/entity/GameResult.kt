package com.example.numbercompositionexample.domain.entity

import java.io.Serializable

// 4) Создаем дата класс
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) :Serializable

// 5) Создаем пакет entity в пакете domain и перемещаем тюда наши классы
// 6) Создаем пакет usecases в domain