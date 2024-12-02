package com.enspd.flightsearchapp.ui.screens.search

import com.enspd.flightsearchapp.model.Airport
import com.enspd.flightsearchapp.model.Favorite

data class SearchUiState(
    val searchQuery: String = "",
    val selectedCode: String = "",
    val airportList: List<Airport> = emptyList(),
    val favoriteList: List<Favorite> = emptyList(),
)