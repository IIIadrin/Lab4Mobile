package com.example.ml4.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ml4.R
import com.example.ml4.navigation.CityScreen
import com.example.ml4.ui.components.CityBottomNavigationBar
import com.example.ml4.ui.components.CityDrawerContent
import com.example.ml4.ui.components.CityTopAppBar
import com.example.ml4.ui.screens.CategoryChoiceScreen
import com.example.ml4.ui.screens.CityListAndDetailsScreen
import com.example.ml4.ui.viewmodel.CityViewModel
import kotlinx.coroutines.launch

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: CityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: CityScreen.CategorySelect.name
    val currentScreen = try { CityScreen.valueOf(currentRoute) } catch (e: Exception) { CityScreen.CategorySelect }

    val isExpanded = windowSize == WindowWidthSizeClass.Expanded
    val showPermanentDrawer = isExpanded && currentScreen != CityScreen.CategorySelect

    if (showPermanentDrawer) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet {
                    CityDrawerContent(
                        categories = uiState.categories,
                        currentCategory = uiState.currentCategory,
                        currentScreen = currentScreen,
                        onCategorySelect = { category ->
                            viewModel.updateCurrentCategory(category)
                            if (currentScreen != CityScreen.Start) {
                                navController.navigate(CityScreen.Start.name)
                            }
                        },
                        onScreenSelect = { screen -> navController.navigate(screen.name) },
                        onHomeClick = { navController.navigate(CityScreen.CategorySelect.name) }
                    )
                }
            }
        ) {
            MainScaffold(currentScreen, uiState, viewModel, navController, isExpanded, onMenuClick = {})
        }
    } else {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                CityDrawerContent(
                    categories = uiState.categories,
                    currentCategory = uiState.currentCategory,
                    currentScreen = currentScreen,
                    onCategorySelect = { category ->
                        viewModel.updateCurrentCategory(category)
                        navController.navigate(CityScreen.Start.name) {
                            popUpTo(CityScreen.CategorySelect.name)
                        }
                        scope.launch { drawerState.close() }
                    },
                    onScreenSelect = { screen ->
                        navController.navigate(screen.name)
                        scope.launch { drawerState.close() }
                    },
                    onHomeClick = {
                        navController.navigate(CityScreen.CategorySelect.name)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        ) {
            MainScaffold(currentScreen, uiState, viewModel, navController, isExpanded) {
                scope.launch { drawerState.open() }
            }
        }
    }
}

@Composable
fun MainScaffold(
    currentScreen: CityScreen,
    uiState: com.example.ml4.ui.viewmodel.CityUiState,
    viewModel: CityViewModel,
    navController: NavHostController,
    isExpanded: Boolean,
    onMenuClick: () -> Unit
) {
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)

    Scaffold(
        topBar = {
            CityTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = currentScreen != CityScreen.CategorySelect,
                onBackClick = {
                    if (!uiState.isShowingListPage && currentScreen == CityScreen.Start) {
                        viewModel.resetHomeScreenStates()
                    } else {
                        navController.popBackStack()
                    }
                },
                onMenuClick = onMenuClick
            )
        },
        bottomBar = {
            if (currentScreen == CityScreen.Start && !isExpanded) {
                CityBottomNavigationBar(
                    categories = uiState.categories,
                    currentCategory = uiState.currentCategory,
                    onCategorySelect = { viewModel.updateCurrentCategory(it) }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CityScreen.CategorySelect.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = CityScreen.CategorySelect.name) {
                CategoryChoiceScreen(
                    categories = uiState.categories,
                    onCategoryClick = { category ->
                        viewModel.updateCurrentCategory(category)
                        navController.navigate(CityScreen.Start.name)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = CityScreen.Start.name) {
                CityListAndDetailsScreen(viewModel = viewModel, modifier = Modifier.fillMaxSize())
            }
            composable(route = CityScreen.About.name) {
                Text(
                    text = stringResource(id = R.string.about_text),
                    modifier = Modifier.padding(paddingMedium)
                )
            }
        }
    }
}