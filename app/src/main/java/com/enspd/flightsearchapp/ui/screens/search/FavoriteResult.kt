package com.enspd.flightsearchapp.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enspd.flightsearchapp.model.Airport
import com.enspd.flightsearchapp.model.Favorite
import com.enspd.flightsearchapp.ui.screens.flight_screen.FlightRow

@Composable
fun FavoriteResult(
    modifier: Modifier = Modifier,
    airportList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (String, String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        items(favoriteList, key = { it.id }) { item ->
            val departAirport = airportList.first { airport -> airport.code == item.departureCode }
            val destinationAirport =
                airportList.first { airport -> airport.code == item.destinationCode }
            Card(
                modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)) {
                FlightRow(
                    isFavorite = true,
                    departureAirportCode = departAirport.code,
                    departureAirportName = departAirport.name,
                    destinationAirportCode = destinationAirport.code,
                    destinationAirportName = destinationAirport.name,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}