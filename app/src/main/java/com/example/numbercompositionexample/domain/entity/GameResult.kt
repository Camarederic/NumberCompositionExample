package com.example.numbercompositionexample.domain.entity

// 4) Создаем дата класс
data class GameResult(
    val winner: Int,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: Int,
)

// 5) Создаем пакет entity в пакете domain и перемещаем тюда наши классы
// 6) Создаем пакет usecases в domain