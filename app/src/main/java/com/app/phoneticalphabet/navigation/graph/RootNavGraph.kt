package com.app.phoneticalphabet.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.phoneticalphabet.ui.screens.MainScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    darkTheme: Boolean,
    toggleDarkTheme: (Boolean) -> Unit,
    showInAppReview: () -> Unit,
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.MAIN
    ) {
        composable(route = Graph.MAIN) {
            MainScreen(
                darkTheme = darkTheme,
                toggleDarkTheme = toggleDarkTheme,
                showInAppReview = showInAppReview
            )
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val QUIZ = "quiz_graph"
    const val ALPHABET = "alphabet_graph"
    const val FLASHCARD = "flashcard_graph"
}
