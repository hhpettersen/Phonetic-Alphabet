package com.example.dinnerplanswitch.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dinnerplanswitch.ui.screens.LoginScreen
import com.example.dinnerplanswitch.ui.screens.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_ROUTE,
    ) {
        composable(
            route = Screen.Login.route,
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.Signup.route,
        ) {
            SignUpScreen(navController = navController)
        }
    }
}