package com.example.ml4.navigation

import androidx.annotation.StringRes
import com.example.ml4.R

enum class CityScreen(@StringRes val title: Int) {
    CategorySelect(title = R.string.app_name),
    Start(title = R.string.nav_home),
    About(title = R.string.nav_about)
}