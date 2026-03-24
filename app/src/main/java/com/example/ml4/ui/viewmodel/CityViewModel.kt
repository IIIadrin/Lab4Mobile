package com.example.ml4.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.ml4.data.LocalDataProvider
import com.example.ml4.data.model.Category
import com.example.ml4.data.model.Place

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentCategory(category: Category) {
        _uiState.update {
            it.copy(
                currentCategory = category,
                currentPlaceList = LocalDataProvider.places.filter { place ->
                    place.categoryId == category.id
                },
                isShowingListPage = true
            )
        }
    }

    fun updateSelectedPlace(place: Place) {
        _uiState.update {
            it.copy(
                selectedPlace = place,
                isShowingListPage = false
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                isShowingListPage = true
            )
        }
    }
}