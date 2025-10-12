package tat.mukhutdinov.lesson9.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tat.mukhutdinov.lesson9.data.Datasource
import tat.mukhutdinov.lesson9.model.Dessert

class MainViewModel : ViewModel() {

    private val dessertList = Datasource.dessertList

    private val _state = MutableStateFlow(
        MainState(
            currentDessertPrice = dessertList.first().price,
            currentDessertImageId = dessertList.first().imageId,
        )
    )
    val state = _state.asStateFlow()

    fun onDessertClicked() {
        _state.update {
            val dessertsSold = it.dessertsSold + 1
            val dessertToShow = determineDessertToShow(dessertList, dessertsSold)

            it.copy(
                revenue = it.revenue + it.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertImageId = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price
            )
        }
    }

    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}