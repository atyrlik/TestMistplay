# Test Mistplay - Alexandre Tyrlik

## Time invested

I spent around 4 hours on this project:
- 2 hours to set up the project (architecture, DI, navigation).
- 1 hour on the list of games (fragment, adapters, layout).
- 1 hour to add tests, and write documentation.

## Technologies

I used the following technologies:
- **Navigation Component**, this was not really needed for only one screen, but it make the project really easy to extend.
- **ViewModel Component and LiveData**, this handle the lifecycle management for us. 
- **Coroutines**, to handle background work. I have more experience with Rx, but I really like the duo Coroutines/Flow, as it feels more Kotlin-like.
- **Koin** for Dependencies Injection, this is a lightweight alternative to Dagger.
I have more experience with Dagger, but I really like Koin for simpler projects, as it does not require as much set-up.
It also works very well with the ViewModel component.
- **Picasso**, to load the games cover images.
- **Gson**, to parse the data from the Json file.

## Architecture

I am using MVVM here, with a repository. The app is divided into the following packages:
- **gameslistscreen**: This contains the views and the viewmodel for the main fragment. I used two adapters, one for the vertical list, and one for the horizontal list.
- **models**: This contains the data classes used in the app. Here there is only two entities, GamesList and Game.
On a bigger project, I would do a separation between the entities we get from the backend/Database, and the models we use in the app.
- **repository**: This contains the repository for our games list. We only use a local storage here, and we load it only one time, so there is no caching involved.

I also like to use use-cases on bigger projects (following the CLEAN architecture), but it didn't make sense to me to use them here.

The *MainModule* and *MainApplication* classes are only to set up Koin.

I like to use a single activity architecture whenever possible.

## Tests

I added a few unit tests for the *GamesListViewModel*.
Since I am using LiveData and Coroutines, there is a bit of boilerplate involved.

There was not really anything else to test in the project, and I wanted to keep the repository as simple as possible.

I did not write any UI tests, as it is quite time consuming. But I have experience writing them with Espresso.

## Improvements

I kept the application really simple and focused on the list of lists.
However some additional features could be added easily, like a details screen when clicking on a game cover, or a search box to find a specific game.