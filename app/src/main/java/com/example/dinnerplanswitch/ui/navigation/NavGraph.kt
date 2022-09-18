package com.example.dinnerplanswitch.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(DETAIL_ARGUMENT_KEY2) {
                    type = NavType.StringType
                },
                navArgument(DETAIL_ARGUMENT_KEY3) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            Log.d("Args", it.arguments?.getString(DETAIL_ARGUMENT_KEY).toString())
            Log.d("Args", it.arguments?.getString(DETAIL_ARGUMENT_KEY2).toString())
            Log.d("Args", it.arguments?.getString(DETAIL_ARGUMENT_KEY3).toString())
            DetailScreen(
                navController = navHostController
            )
        }
    }
}