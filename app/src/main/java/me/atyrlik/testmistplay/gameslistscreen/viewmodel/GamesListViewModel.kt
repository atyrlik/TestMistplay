package me.atyrlik.testmistplay.gameslistscreen.viewmodel

import androidx.lifecycle.*
import me.atyrlik.testmistplay.models.GamesList
import me.atyrlik.testmistplay.repository.GamesRepository

class GamesListViewModel(
    private val repository: GamesRepository
) : ViewModel() {

    enum class LoadingState {
        LOADING, READY, ERROR
    }

    val loadingState = MutableLiveData<LoadingState>(LoadingState.LOADING)

    val games: LiveData<List<GamesList>> = liveData(context = viewModelScope.coroutineContext) {
        repository.getGames().fold(
            onSuccess = {
                emit(it)
                loadingState.postValue(LoadingState.READY)
            },
            onFailure = {
                loadingState.postValue(LoadingState.ERROR)
            }
        )
    }

}