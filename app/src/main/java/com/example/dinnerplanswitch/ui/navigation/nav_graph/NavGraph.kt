package com.example.dinnerplanswitch.ui.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dinnerplanswitch.ui.navigation.*

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        route = ROOT_ROUTE,
    ) {
        homeNavGraph(navController = navController)
        authNavGraph(navController = navController)
    }
}
