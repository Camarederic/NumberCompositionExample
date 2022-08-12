package com.example.numbercompositionexample.domain.entity


import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// 4) Создаем дата класс
@Parcelize
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) :Parcelable // 64) Serializable меняем на Parcelable

// 5) Создаем пакет entity в пакете domain и перемещаем тюда наши классы
// 6) Создаем пакет usecases в domain