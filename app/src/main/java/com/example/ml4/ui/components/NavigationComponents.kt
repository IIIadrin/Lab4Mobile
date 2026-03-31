package com.example.ml4.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.ml4.R
import com.example.ml4.data.model.Category
import com.example.ml4.navigation.CityScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityTopAppBar(
    currentScreen: CityScreen,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            } else {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(id = R.string.menu_button)
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun CityBottomNavigationBar(
    categories: List<Category>,
    currentCategory: Category,
    onCategorySelect: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        categories.forEach { category ->
            NavigationBarItem(
                selected = currentCategory == category,
                onClick = { onCategorySelect(category) },
                icon = { Icon(imageVector = category.icon, contentDescription = null) },
                label = {
                    Text(
                        text = stringResource(id = category.titleRes),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            )
        }
    }
}

@Composable
fun CityDrawerContent(
    categories: List<Category>,
    currentCategory: Category,
    currentScreen: CityScreen,
    onCategorySelect: (Category) -> Unit,
    onScreenSelect: (CityScreen) -> Unit,
    onHomeClick: () -> Unit
) {
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val paddingSmall = dimensionResource(id = R.dimen.padding_small)
    val scrollState = rememberScrollState()

    ModalDrawerSheet(
        modifier = Modifier.width(dimensionResource(id = R.dimen.drawer_width))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(paddingMedium)
            )
            HorizontalDivider()
            Spacer(modifier = Modifier.height(paddingSmall))

            NavigationDrawerItem(
                label = { Text(text = stringResource(id = R.string.nav_home)) },
                selected = currentScreen == CityScreen.CategorySelect,
                onClick = onHomeClick,
                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = paddingSmall))

            categories.forEach { category ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(id = category.titleRes),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = (currentCategory == category && currentScreen == CityScreen.Start),
                    onClick = { onCategorySelect(category) },
                    icon = { Icon(imageVector = category.icon, contentDescription = null) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = paddingSmall))

            NavigationDrawerItem(
                label = { Text(text = stringResource(id = R.string.nav_about)) },
                selected = currentScreen == CityScreen.About,
                onClick = { onScreenSelect(CityScreen.About) },
                icon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
            Spacer(modifier = Modifier.height(paddingMedium))
        }
    }
}