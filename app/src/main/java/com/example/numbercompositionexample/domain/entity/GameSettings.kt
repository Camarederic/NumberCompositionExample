package com.example.numbercompositionexample.domain.entity



import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// 2) Создаем дата класс с параметрами
@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int,
): Parcelable  // 63) Serializable меняем на Parcelable

