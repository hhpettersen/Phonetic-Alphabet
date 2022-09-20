package com.example.dinnerplanswitch.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.dinnerplanswitch.ui.navigation.*
import com.example.dinnerplanswitch.ui.screens.DetailScreen
import com.example.dinnerplanswitch.ui.screens.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE,
    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(
                navController = navController
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
                navController = navController
            )
        }
    }

}
