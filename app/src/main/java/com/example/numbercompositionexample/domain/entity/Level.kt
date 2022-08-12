package com.example.numbercompositionexample.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// 1) Создаем класс enum и перечисляем в нем какие уровни будут
@Parcelize
enum class Level:Parcelable  {
    TEST, EASY, NORMAL, HARD
}