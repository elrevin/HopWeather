# Weather Forecast App

An example Android application for viewing weather forecasts. The main goal is to demonstrate the principles of clean architecture in Kotlin using Jetpack Compose, Coroutines, Flows, and Retrofit.

## Features
### Location search and weather tracking
![1](https://github.com/elrevin/HopWeather/assets/8909121/d9f545dd-f92a-4da0-89e7-d560f68630ae)

### Displaying today's and tomorrow's weather
![4](https://github.com/elrevin/HopWeather/assets/8909121/82277324-024a-4168-a862-893e0b792678)


### Showing a 7-day weather forecast
![2](https://github.com/elrevin/HopWeather/assets/8909121/3d81ef97-d700-43a6-830f-8bfef9a0becf)

### Changing the header image (frog character) based on the season, time of day, and weather conditions
![autumn-clear-day](https://github.com/elrevin/HopWeather/assets/8909121/44e31eee-5456-431a-b5b1-8c63442f4061)
![autumn-clear-night](https://github.com/elrevin/HopWeather/assets/8909121/93094c75-bb6b-4472-be39-892a47b6d7a9)
![autumn-snow-day](https://github.com/elrevin/HopWeather/assets/8909121/7c9bacf6-3dd5-4faa-ac4c-fa3b5e939f8d)
etc.

## Project Structure

The application consists of the following modules:
- **app**: The main module containing the Application class and the main Activity.
- **core**: Common resources and functions accessible to all other modules. This module does not have any other dependencies.
- **data**: Module for data source handling, including classes for data retrieval (external API implementation), data storage (internal SQLite/ROOM database), and data access (repositories).
- **domain**: Module containing common abstractions and data structures, as well as classes implementing business logic (UseCases).
- **presentation**: Module implementing user interfaces and views.

## Dependency Injection

Dagger Hilt is used for dependency injection. Each module that provides objects has a corresponding DI module describing dependencies.

## MVI Architecture

The application follows the Model-View-Intent (MVI) architecture. A ViewModel is used to provide the screen state and handle incoming events.

## Data Communication Between Components

Flows are used for data communication between components.

## Video Demonstration

[Watch a video demonstration of the app](#) (link to be inserted)

## Contact
- **Eugene Burmitsky**
- LinkedIn: [https://www.linkedin.com/in/eugene-burmitsky/](https://www.linkedin.com/in/eugene-burmitsky/)

Feel free to reach out if you have any questions or feedback!
