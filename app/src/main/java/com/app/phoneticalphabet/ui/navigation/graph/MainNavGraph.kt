package com.app.phoneticalphabet.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.phoneticalphabet.BottomBarScreen
import com.app.phoneticalphabet.ui.screens.random.ScreenContent
import com.app.phoneticalphabet.ui.screens.home.HomeScreen
import com.app.phoneticalphabet.ui.screens.quiz.QuizScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                onAlphabetClicked = {},
                onFlashCardsClicked = {},
                onQuizClicked = { navController.navigate(Graph.QUIZ) }
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ScreenContent(
                name = BottomBarScreen.Profile.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Settings.route) {
            ScreenContent(
                name = BottomBarScreen.Settings.route,
                onClick = { }
            )
        }
        quizNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.quizNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.QUIZ,
        startDestination = QuizScreen.Quiz.route
    ) {
        composable(route = QuizScreen.Quiz.route) {
            QuizScreen { finalScore ->
                navController.navigate(QuizScreen.Result.route) {
                    popUpTo(BottomBarScreen.Home.route) {
                        inclusive = false
                    }
                }
            }
        }
        composable(route = QuizScreen.Result.route) {
            ScreenContent(name = QuizScreen.Result.route) {
                navController.popBackStack(
                    route = QuizScreen.Quiz.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class QuizScreen(val route: String) {
    object Quiz : QuizScreen(route = "QUIZ")
    object Result : QuizScreen(route = "RESULT")
}
