package com.enspd.flightsearchapp.ui.screens.flight_screen

import com.enspd.flightsearchapp.model.Airport
import com.enspd.flightsearchapp.model.Favorite

data class FlightsUiState(
    val code: String = "",
    val favoriteList: List<Favorite> = emptyList(),
    val destinationList: List<Airport> = emptyList(),
    val departureAirport: Airport = Airport(),
)
