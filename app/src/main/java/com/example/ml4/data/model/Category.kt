package com.example.ml4.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: Int,
    @StringRes val titleRes: Int,
    val icon: ImageVector
)