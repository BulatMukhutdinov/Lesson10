package tat.mukhutdinov.lesson9.ui

import androidx.annotation.DrawableRes

data class MainState(
    val currentDessertPrice: Int,
    @DrawableRes
    val currentDessertImageId: Int,
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
)