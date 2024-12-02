package com.enspd.flightsearchapp.ui.screens.search

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.enspd.flightsearchapp.FlightApplication
import com.enspd.flightsearchapp.data.FlightRepository
import com.enspd.flightsearchapp.data.UserPreferencesRepository
import com.enspd.flightsearchapp.model.Airport
import com.enspd.flightsearchapp.model.Favorite
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    val flightRepository: FlightRepository,
    private val userPreferencesRepository: UserPreferencesRepository

    ) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private var deletedRecord: Favorite? = null

    private var getAirportsJob: Job? = null

    private var airportList = mutableListOf<Airport>()// MutableState<List<Favorite>>(emptyList())
    // Favorites from the Favorite Table
    private var favoriteList = mutableListOf<Favorite>()// MutableState<List<Favorite>>(emptyList())


    init {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.collect {
                processSearchQueryFlow(it.searchValue)
            }
        }
    }


    fun onQueryChange(query: String) {
        processSearchQueryFlow(query)
    }

    private fun processSearchQueryFlow(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

        if (query.isEmpty()) {
            viewModelScope.launch {
                airportList = flightRepository.getAllAirports().toMutableStateList()
                favoriteList= flightRepository.getAllFavoritesFlights().toMutableStateList()
                _uiState.update {
                    uiState.value.copy(
                        airportList = airportList,
                        favoriteList = favoriteList
                    )
                }
            }
        } else {
            getAirportsJob?.cancel()

            getAirportsJob = flightRepository.getAllAirportsFlow(query)
                // on each emission
                .onEach { result ->
                    _uiState.update {
                        uiState.value.copy(
                            airportList = result,
                        )
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun updateQuery(searchQuery: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = searchQuery,
            )
        }
        updatePreferenceSearchValue(searchQuery)
    }

    fun updateSelectedCode(selectedCode: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCode = selectedCode,
            )
        }
    }

    fun removeDbFavorite(record: Favorite) {
        viewModelScope.launch {
            deletedRecord = record

            flightRepository.deleteFavoriteFlight(record)

            val xx = uiState.value.favoriteList.toMutableStateList()

            xx.remove(record)
            _uiState.update {
                uiState.value.copy(
                    favoriteList = xx,
                )
            }

        }
    }

    fun updatePreferenceSearchValue(newValue: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateUserPreferences(searchValue = newValue)
        }
    }


    /**
     * Factory for BookshelfViewModel] that takes BookshelfRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as? FlightApplication ?: throw IllegalStateException("Application is not of type FlightApplication"))
                val flightRepository = application.container.flightRepository
                val preferencesRepository = application.userPreferencesRepository
                SearchViewModel(
                    flightRepository = flightRepository,
                    userPreferencesRepository = preferencesRepository
                )
            }
        }
    }
}