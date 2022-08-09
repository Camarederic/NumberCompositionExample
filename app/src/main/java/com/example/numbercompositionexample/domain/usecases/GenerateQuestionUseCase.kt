package com.example.numbercompositionexample.domain.usecases

import com.example.numbercompositionexample.domain.entity.Question
import com.example.numbercompositionexample.domain.repository.GameRepository

// 8) Создаем usecase класс будет генерировать вопрос
class GenerateQuestionUseCase(private val repository: GameRepository) {

    // 12) Создаем метод, но чтобы не называть метод также одиково как название класса, то
    // в котлин есть специальное название
    operator fun invoke(maxSumValue: Int) : Question{
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    // 13) Создаем константу для количества вариантов ответов
    private companion object{
      private const val COUNT_OF_OPTIONS = 6
    }
}

// 9) Создаем пакет репозитория в domain