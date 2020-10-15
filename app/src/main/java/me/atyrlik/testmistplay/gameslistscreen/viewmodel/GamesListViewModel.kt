package me.atyrlik.testmistplay.gameslistscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import me.atyrlik.testmistplay.models.GamesList
import me.atyrlik.testmistplay.repository.GamesRepository

class GamesListViewModel(
    private val repository: GamesRepository
) : ViewModel() {

    val games: LiveData<List<GamesList>> = liveData(context = viewModelScope.coroutineContext) {
        repository.getGames().fold(
            onSuccess = { emit(it) },
            onFailure = {} // Don't emit anything is there is an error. We should display something to the user.
        )
    }

}