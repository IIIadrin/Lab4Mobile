package com.example.ml4.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.ml4.R
import com.example.ml4.data.model.Category

@Composable
fun CategoryChoiceScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.category_prompt),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(paddingMedium)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(paddingMedium),
            verticalArrangement = Arrangement.spacedBy(paddingMedium),
            horizontalArrangement = Arrangement.spacedBy(paddingMedium),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categories) { category ->
                CategoryCard(
                    category = category,
                    onClick = { onCategoryClick(category) }
                )
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val paddingSmall = dimensionResource(id = R.dimen.padding_small)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingSmall),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen.list_item_image_size)),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(paddingSmall))
            Text(
                text = stringResource(id = category.titleRes),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 2,
                softWrap = true
            )
        }
    }
}