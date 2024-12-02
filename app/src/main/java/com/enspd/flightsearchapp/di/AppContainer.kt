package com.enspd.flightsearchapp.di

import android.content.Context
import com.enspd.flightsearchapp.data.FlightDatabase
import com.enspd.flightsearchapp.data.FlightRepository
import com.enspd.flightsearchapp.data.OfflineFlightRepository



/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val flightRepository: FlightRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineFlightRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [FlightRepository]
     */
    override val flightRepository: FlightRepository by lazy {
        OfflineFlightRepository(FlightDatabase.getDatabase(context).flightDao())
    }
}