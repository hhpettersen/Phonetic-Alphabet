package com.example.dinnerplanswitch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        route = ROOT_ROUTE,
    ) {
        homeNavGraph(navController = navController)
        authNavGraph(navController = navController)
    }
}
