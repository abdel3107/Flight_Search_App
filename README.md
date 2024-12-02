# Flight Search App

Flight Search App is a Kotlin-based Android application that allows users to search for flights and manage their favorite flights. The app uses Jetpack Compose for the UI and Android Navigation Component for navigation.

## Features

- Search for flights by airport code
- View flight results
- Add and remove favorite flights
- Navigate between search and flight screens

## Screenshots

### Home
<img alt="Home" src="screenshots\1.png" width="250"/>

### Search
<img alt="Search" src="screenshots\2 searching.png" width="250"/>

### Chosen One
<img alt="Chosen One" src="screenshots\3 chosen one.png" width="250"/>

### Favorite
<img alt="Favorite" src="screenshots\4 added favourites.png" width="250"/>

### Back to home
<img alt="Back to home" src="screenshots\5 back to home.png" width="250"/>

### Deleted one favourite
<img alt="Deleted one favourite" src="screenshots\6 deleted one favourite.png" width="250"/>


## Installation

1. Clone the repository:
    ```sh
    git clone 
    ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.

## Dependencies

The project uses the following dependencies:

- Jetpack Compose
- Room
- KSP
- Preference DataStore
- Android Navigation Component
- Material3
- ViewModel

## Usage

### Search Screen

The search screen allows users to search for flights by entering an airport code. The results are displayed in a list, and users can select a flight to view more details.

### Flight Screen

The flight screen displays the details of the selected flight. Users can add or remove the flight from their favorites.

## Code Structure

- `MainActivity.kt`: The main activity that sets up the navigation and top app bar.
- `FlightSearchApp.kt`: The composable function that sets up the navigation host.
- `ui/screens/search/SearchScreen.kt`: The composable function for the search screen.
- `ui/screens/flight_screen/FlightScreen.kt`: The composable function for the flight screen.
- `ui/screens/search/SearchResults.kt`: The composable function for displaying search results.
- `ui/screens/search/FavoriteResult.kt`: The composable function for displaying favorite flights.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License
...
