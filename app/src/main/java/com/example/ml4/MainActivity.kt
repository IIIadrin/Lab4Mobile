package com.example.ml4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.ml4.ui.CityApp
import com.example.ml4.ui.theme.Ml4Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ml4Theme {
                val windowSize = calculateWindowSizeClass(this)

                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CityApp(windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }
}