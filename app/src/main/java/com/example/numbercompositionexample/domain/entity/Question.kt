package com.example.numbercompositionexample.domain.entity

// 3) Создаем дата класс
data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val answerOptions: List<Int>,
)