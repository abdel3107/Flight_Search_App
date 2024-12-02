package com.enspd.flightsearchapp.ui.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enspd.flightsearchapp.NavigationDestination
import com.enspd.flightsearchapp.R
import com.enspd.flightsearchapp.model.Favorite

object SearchDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSelectCode: (String) -> Unit
) {
    val viewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
    val uiState = viewModel.uiState.collectAsState().value

    Column( modifier = modifier.padding(16.dp)) {
        SearchTextField(
            uiState.searchQuery,
            onQueryChange = {
                viewModel.updateQuery(it)
                viewModel.updateSelectedCode("")
                viewModel.onQueryChange(it)
            }
        )

        if (uiState.searchQuery.isEmpty()) {

            val favoriteList = uiState.favoriteList
            val airportList = uiState.airportList

            if (favoriteList.isNotEmpty()){
                FavoriteResult(
                    airportList = airportList,
                    favoriteList = favoriteList,
                    onFavoriteClick = { departureCode: String, destinationCode: String ->
                        val tmp = Favorite(
                            id = favoriteList.filter {
                                    xxx -> (xxx.departureCode == departureCode && xxx.destinationCode == destinationCode)
                            }.first().id,
                            departureCode = departureCode,
                            destinationCode = destinationCode,
                        )
                        viewModel.removeDbFavorite(tmp)
                    },
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Favorites yet",
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {

            val airports = uiState.airportList

            SearchResults(
                airports = airports,
                onSelectCode = onSelectCode
            )
        }
    }
}