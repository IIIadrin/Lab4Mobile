package com.example.ml4.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Int,
    @StringRes val nameRes: Int,
    @StringRes val shortDescriptionRes: Int,
    @StringRes val longDescriptionRes: Int,
    @DrawableRes val imageRes: Int,
    val categoryId: Int
)