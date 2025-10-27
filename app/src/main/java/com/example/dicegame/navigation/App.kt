package com.example.dicegame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.dicegame.screens.DiceGameScreen
import com.example.dicegame.screens.PlayerNameScreen
import com.example.dicegame.screens.SplashScreen
import com.example.dicegame.screens.WinnerScreen

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController, startDestination = Routes.SplashScreen){

        composable<Routes.SplashScreen> {
            SplashScreen(navController)
        }
        composable<Routes.PlayerNameScreen> {
            PlayerNameScreen(navController)
        }
        composable<Routes.DiceGameScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.DiceGameScreen>() //it convert into object
            DiceGameScreen(
                 navController,
                Player1 = args.Player1,
                Player2 = args.Player2
            )
        }
        composable<Routes.WinnerScreen> {backStackEntry->
            val args=backStackEntry.toRoute<Routes.WinnerScreen>()
            WinnerScreen(
                navController,
                Winner=args.Winner

            )
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavigationGraph(navController=navController)
}