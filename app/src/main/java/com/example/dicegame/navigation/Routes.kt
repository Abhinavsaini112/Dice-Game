package com.example.dicegame.navigation

import kotlinx.serialization.Serializable


sealed class Routes{

    @Serializable
    object SplashScreen

    @Serializable
    object PlayerNameScreen

    @Serializable
    data class DiceGameScreen(
        val Player1: String,
        val Player2: String
    )

    @Serializable
    data class WinnerScreen(
        val Winner:String
    )

}