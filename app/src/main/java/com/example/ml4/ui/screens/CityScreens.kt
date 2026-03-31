package com.example.ml4.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ml4.R
import com.example.ml4.data.model.Place
import com.example.ml4.ui.viewmodel.CityViewModel

@Composable
fun CityListAndDetailsScreen(
    viewModel: CityViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)

    if (!uiState.isShowingListPage) {
        BackHandler {
            viewModel.resetHomeScreenStates()
        }
    }

    if (uiState.isShowingListPage) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(paddingMedium),
            verticalArrangement = Arrangement.spacedBy(paddingMedium)
        ) {
            items(uiState.currentPlaceList) { place ->
                PlaceListItem(
                    place = place,
                    onPlaceClick = { viewModel.updateSelectedPlace(it) }
                )
            }
        }
    } else {
        PlaceDetailScreen(
            place = uiState.selectedPlace,
            modifier = modifier
        )
    }
}

@Composable
fun PlaceListItem(
    place: Place,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val paddingSmall = dimensionResource(id = R.dimen.padding_small)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onPlaceClick(place) },
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.card_elevation)),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.list_item_image_size))
                    .padding(paddingSmall),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = paddingSmall)
            ) {
                Text(
                    text = stringResource(id = place.nameRes),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(id = place.shortDescriptionRes),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun PlaceDetailScreen(place: Place, modifier: Modifier = Modifier) {
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val paddingSmall = dimensionResource(id = R.dimen.padding_small)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = place.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.detail_image_height)),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(paddingMedium)) {
            Text(
                text = stringResource(id = place.nameRes),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(paddingSmall))
            Text(
                text = stringResource(id = place.longDescriptionRes),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}