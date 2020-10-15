package me.atyrlik.testmistplay.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.atyrlik.testmistplay.R
import me.atyrlik.testmistplay.models.GamesList

interface GamesRepository {
    /**
     * Fetch games list from local json file.
     */
    suspend fun getGames():  Result<List<GamesList>>
}

class GamesRepositoryImpl(private val context: Context): GamesRepository {
    override suspend fun getGames(): Result<List<GamesList>> = withContext(Dispatchers.IO) {
        // we only have a json file for the data, so let's keep it simple
        try {
            val jsonString = context.resources.openRawResource(R.raw.games).bufferedReader().use {
                it.readText()
            }
            val typeToken = object : TypeToken<List<GamesList>>() {}.type
            Result.success(Gson().fromJson<List<GamesList>>(jsonString, typeToken))
        } catch (e: Exception) {
            Result.failure<List<GamesList>>(e)
        }
    }
}
