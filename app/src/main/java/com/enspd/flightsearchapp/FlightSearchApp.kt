package com.enspd.flightsearchapp


//import com.enspd.flightsearchapp.ui.screens.flight_screen.FlightScreen
//import com.enspd.flightsearchapp.ui.screens.flight_screen.FlightScreenDestination
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enspd.flightsearchapp.ui.screens.flight_screen.FlightScreen
import com.enspd.flightsearchapp.ui.screens.flight_screen.FlightScreenDestination
import com.enspd.flightsearchapp.ui.screens.search.SearchDestination
import com.enspd.flightsearchapp.ui.screens.search.SearchScreen


@Composable
fun FlightSearchApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    Scaffold() { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = SearchDestination.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = SearchDestination.route) {
                SearchScreen(
                    modifier = Modifier.padding(paddingValues),
                    onSelectCode = {
                        navController.navigate("${FlightScreenDestination.route}/${it}")
                    }
                )
            }
            composable(
                route = FlightScreenDestination.routeWithArgs,
                arguments = listOf(navArgument(FlightScreenDestination.codeArg) {
                    type = NavType.StringType
                }
                )
            ) {
                FlightScreen()

            }
        }
    }
}