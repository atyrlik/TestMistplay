package me.atyrlik.testmistplay

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import me.atyrlik.testmistplay.gameslistscreen.viewmodel.GamesListViewModel
import me.atyrlik.testmistplay.repository.GamesRepository
import org.junit.Test
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import me.atyrlik.testmistplay.models.GamesList
import org.junit.After

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class GamesListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: GamesListViewModel
    private lateinit var gamesRepository: GamesRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        gamesRepository = mock()
        viewModel = GamesListViewModel(
            gamesRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `fetching the games list should emit it as success`() = runBlockingTest {
        // given that games can be fetched successfully
        whenever(gamesRepository.getGames()).thenReturn(
            Result.success(listOf(GamesList("title", emptyList())))
        )

        // when the viewmodel fetch the games
        val observer: Observer<List<GamesList>> = mock()
        viewModel.games.observeForever(observer)

        // then the viewmodel emit the games as success
        verify(gamesRepository).getGames()
        assertEquals(
            listOf(GamesList("title", emptyList())),
            viewModel.games.value
        )
        assertEquals(
            GamesListViewModel.LoadingState.READY,
            viewModel.loadingState.value
        )
    }

    @Test
    fun `failing to fetch the games list should emit an error`() = runBlockingTest {
        // given that games can not be fetched
        whenever(gamesRepository.getGames()).thenReturn(
            Result.failure(Exception())
        )

        // when the viewmodel fetch the games
        val observer: Observer<List<GamesList>> = mock()
        viewModel.games.observeForever(observer)

        // then the viewmodel emit an error
        verify(gamesRepository).getGames()
        assertEquals(
            null,
            viewModel.games.value
        )
        assertEquals(
            GamesListViewModel.LoadingState.ERROR,
            viewModel.loadingState.value
        )
    }

    @Test
    fun `fetching the games list should update the loading state`() = runBlockingTest {
        // given that games can be fetched successfully
        whenever(gamesRepository.getGames()).thenReturn(
            Result.success(listOf(GamesList("title", emptyList())))
        )

        // when the viewmodel fetch the games
        val observerLoadingState: Observer<GamesListViewModel.LoadingState> = mock()
        val observerGame: Observer<List<GamesList>> = mock()
        viewModel.loadingState.observeForever(observerLoadingState)
        viewModel.games.observeForever(observerGame)

        // then the viewmodel should update the loading state
        inOrder(observerLoadingState) {
            verify(observerLoadingState).onChanged(GamesListViewModel.LoadingState.LOADING)
            verify(observerLoadingState).onChanged(GamesListViewModel.LoadingState.READY)
        }
    }
}