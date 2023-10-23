# Weather Forecast App

An example Android application for viewing weather forecasts. The main goal is to demonstrate the principles of clean architecture in Kotlin using Jetpack Compose, Coroutines, Flows, and Retrofit.

## Features
- Location search and weather tracking
- Displaying today's and tomorrow's weather
- Showing a 7-day weather forecast
- Changing the header image (frog character) based on the season, time of day, and weather conditions

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
