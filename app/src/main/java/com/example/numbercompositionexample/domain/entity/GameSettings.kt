package com.example.numbercompositionexample.domain.entity

import java.io.Serializable

// 2) Создаем дата класс с параметрами
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int,
): Serializable