package me.atyrlik.testmistplay

import me.atyrlik.testmistplay.gameslistscreen.viewmodel.GamesListViewModel
import me.atyrlik.testmistplay.repository.GamesRepository
import me.atyrlik.testmistplay.repository.GamesRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// This could be divided in multiple modules in a bigger app.
val mainModule: Module = module {
    viewModel { GamesListViewModel(get()) }
    single<GamesRepository> { GamesRepositoryImpl(get()) }
}