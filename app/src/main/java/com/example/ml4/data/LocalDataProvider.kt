package com.example.ml4.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Restaurant
import com.example.ml4.R
import com.example.ml4.data.model.Category
import com.example.ml4.data.model.Place

object LocalDataProvider {
    val categories = listOf(
        Category(1, R.string.category_coffee, Icons.Default.Coffee),
        Category(2, R.string.category_museums, Icons.Default.Museum),
        Category(3, R.string.category_sights, Icons.Default.Landscape),
        Category(4, R.string.category_restaurants, Icons.Default.Restaurant)
    )

    val places = listOf(
        Place(101, R.string.gs_name, R.string.gs_short, R.string.gs_long, R.drawable.gs_coffeeshop, 1),
        Place(102, R.string.prussia_name, R.string.prussia_short, R.string.prussia_long, R.drawable.coffee_one, 1),
        Place(103, R.string.port_name, R.string.port_short, R.string.port_long, R.drawable.port_o_coffe, 1),
        Place(104, R.string.elev_name, R.string.elev_short, R.string.elev_long, R.drawable.elevator_coffe, 1),

        Place(201, R.string.amber_name, R.string.amber_short, R.string.amber_long, R.drawable.museum_amber, 2),
        Place(202, R.string.ocean_name, R.string.ocean_short, R.string.ocean_long, R.drawable.museum_ocean, 2),
        Place(203, R.string.kant_name, R.string.kant_short, R.string.kant_long, R.drawable.cathedral, 2),
        Place(204, R.string.bunker_name, R.string.bunker_short, R.string.bunker_long, R.drawable.museum_bunker, 2),

        Place(301, R.string.village_name, R.string.village_short, R.string.village_long, R.drawable.fishy_village, 3),
        Place(302, R.string.gates_name, R.string.gates_short, R.string.gates_long, R.drawable.kings_gate, 3),
        Place(303, R.string.fort5_name, R.string.fort5_short, R.string.fort5_long, R.drawable.fort_five, 3),
        Place(304, R.string.park_name, R.string.park_short, R.string.park_long, R.drawable.central_park, 3),

        Place(401, R.string.zotler_name, R.string.zotler_short, R.string.zotler_long, R.drawable.rest_zotler, 4),
        Place(402, R.string.salt_name, R.string.salt_short, R.string.salt_long, R.drawable.gastro_bar, 4),
        Place(403, R.string.kaiser_name, R.string.kaiser_short, R.string.kaiser_long, R.drawable.kaizer, 4),
        Place(404, R.string.fish_name, R.string.fish_short, R.string.fish_long, R.drawable.fish_club, 4)
    )
}