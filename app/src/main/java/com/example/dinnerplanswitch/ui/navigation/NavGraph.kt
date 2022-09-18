package com.example.dinnerplanswitch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dinnerplanswitch.ui.screens.DetailScreen
import com.example.dinnerplanswitch.ui.screens.HomeScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(
                navController = navHostController
            )
        }
        composable(
            route = Screen.Detail.route,
        ) {
            DetailScreen(
                navController = navHostController
            )
        }
    }
}