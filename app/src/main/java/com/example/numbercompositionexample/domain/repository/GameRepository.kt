package com.example.numbercompositionexample.domain.repository

import com.example.numbercompositionexample.domain.entity.GameSettings
import com.example.numbercompositionexample.domain.entity.Question

// 10) Создаем интерфейс
interface GameRepository {

    // 11) Создаем два метода
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(): GameSettings
}