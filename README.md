# Weather Forecast App

An example Android application for viewing weather forecasts. The main goal is to demonstrate the principles of clean architecture in Kotlin using Jetpack Compose, Coroutines, Flows, and Retrofit.

## Features
### Location search and weather tracking
<img src="https://github.com/elrevin/HopWeather/assets/8909121/d9f545dd-f92a-4da0-89e7-d560f68630ae" width=250/>

### Displaying today's and tomorrow's weather
<img src="https://github.com/elrevin/HopWeather/assets/8909121/82277324-024a-4168-a862-893e0b792678" width=250/>


### Showing a 7-day weather forecast
<img src="https://github.com/elrevin/HopWeather/assets/8909121/3d81ef97-d700-43a6-830f-8bfef9a0becf" width=250/>

### Changing the header image (frog character) based on the season, time of day, and weather conditions
<img src="https://github.com/elrevin/HopWeather/assets/8909121/44e31eee-5456-431a-b5b1-8c63442f4061" width=100/> <img src="https://github.com/elrevin/HopWeather/assets/8909121/93094c75-bb6b-4472-be39-892a47b6d7a9" width=100/> <img src="https://github.com/elrevin/HopWeather/assets/8909121/7c9bacf6-3dd5-4faa-ac4c-fa3b5e939f8d" width=100/> <img src="https://github.com/elrevin/HopWeather/assets/8909121/fe0681c6-36c0-4723-8202-ffd40fc0f525" width=100/>

etc.

## Project Structure

The application consists of the following modules:
- **[app](https://github.com/elrevin/HopWeather/tree/master/app)**: The main module containing the Application class and the main Activity.
- [**core**](https://github.com/elrevin/HopWeather/tree/master/core): Common resources and functions accessible to all other modules. This module does not have any other dependencies.
- [**data**](https://github.com/elrevin/HopWeather/tree/master/data): Module for data source handling, including classes for data retrieval (external API implementation), data storage (internal SQLite/ROOM database), and data access (repositories).
- [**domain**](https://github.com/elrevin/HopWeather/tree/master/domain): Module containing common abstractions and data structures, as well as classes implementing business logic (UseCases).
- [**presentation**](https://github.com/elrevin/HopWeather/tree/master/presentation): Module implementing user interfaces and views.

## Dependency Injection

Dagger Hilt is used for dependency injection. Each module that provides objects has a corresponding DI module describing dependencies.

## MVI Architecture

The application follows the Model-View-Intent (MVI) architecture. A ViewModel is used to provide the screen state and handle incoming events.

## Data Communication Between Components

Flows are used for data communication between components.

## Video Demonstration

https://github.com/elrevin/HopWeather/assets/8909121/57525a97-5fb8-435b-b028-3b3a8a949e74



## Contact
- **Eugene Burmitsky**
- LinkedIn: [https://www.linkedin.com/in/eugene-burmitsky/](https://www.linkedin.com/in/eugene-burmitsky/)

Feel free to reach out if you have any questions or feedback!
