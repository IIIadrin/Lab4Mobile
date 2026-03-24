package com.example.ml4.ui.viewmodel

import com.example.ml4.data.LocalDataProvider
import com.example.ml4.data.model.Category
import com.example.ml4.data.model.Place

data class CityUiState(
    val categories: List<Category> = LocalDataProvider.categories,
    val currentCategory: Category = LocalDataProvider.categories[0],
    val currentPlaceList: List<Place> = LocalDataProvider.places.filter { it.categoryId == 1 },
    val selectedPlace: Place = LocalDataProvider.places[0],
    val isShowingListPage: Boolean = true
)