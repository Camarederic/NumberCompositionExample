package com.example.numbercompositionexample.domain.usecases

import com.example.numbercompositionexample.domain.entity.GameSettings
import com.example.numbercompositionexample.domain.entity.Level
import com.example.numbercompositionexample.domain.repository.GameRepository

// 7) Создаем usecase класс будет загружать настройки игры в зависимости от уровня
class GetGameSettingsUseCase(private val repository: GameRepository) {

    // 14) Создаем метод
    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings()
    }
}