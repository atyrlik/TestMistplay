package me.atyrlik.testmistplay.models

import com.google.gson.annotations.SerializedName

data class GamesList(
    @SerializedName("list_title")
    val title: String,
    val games: List<Game>
)

data class Game(
    val title: String,
    val img: String
)