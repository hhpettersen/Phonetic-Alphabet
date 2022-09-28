package com.app.phoneticalphabet.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.phoneticalphabet.BottomBarScreen
import com.app.phoneticalphabet.ui.screens.alphabet.AlphabetScreen
import com.app.phoneticalphabet.ui.screens.home.HomeScreen
import com.app.phoneticalphabet.ui.screens.quiz.QuizScreen
import com.app.phoneticalphabet.ui.screens.random.ScreenContent
import com.app.phoneticalphabet.ui.screens.result.ResultScreen

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
                onAlphabetClicked = { navController.fromHomeToAlphabet() },
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
        alphabetGraph(navController = navController)
        quizNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.alphabetGraph(navController: NavHostController) {
    navigation(
        route = Graph.ALPHABET,
        startDestination = AlphabetScreen.Alphabet.route,
    ) {
        composable(route = AlphabetScreen.Alphabet.route) {
            AlphabetScreen()
        }
    }
}

fun NavGraphBuilder.quizNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.QUIZ,
        startDestination = QuizScreen.Quiz.route
    ) {
        composable(route = QuizScreen.Quiz.route) {
            QuizScreen { score -> navController.fromQuizToResult(score) }
        }
        composable(
            route = "${QuizScreen.Result.route}/{score}",
            arguments = listOf(
                navArgument("score") {
                    type = NavType.IntType
                }
            )
        ) {
            ResultScreen(
                onNewGame = { navController.fromResultToQuiz() },
                onFinish = { navController.fromResultToHome() }
            )
        }
    }
}

private fun NavHostController.fromQuizToResult(score: Int) {
    navigate("${QuizScreen.Result.route}/$score") {
        popUpTo(BottomBarScreen.Home.route) {
            inclusive = false
        }
    }
}

private fun NavHostController.fromResultToQuiz() {
    navigate(route = QuizScreen.Quiz.route) {
        popUpTo(BottomBarScreen.Home.route) {
            inclusive = false
        }
    }
}

private fun NavHostController.fromResultToHome() {
    navigate(Graph.MAIN) {
        popUpTo(BottomBarScreen.Home.route) {
            inclusive = true
        }
    }
}

private fun NavHostController.fromHomeToAlphabet() {
    navigate(Graph.ALPHABET) {
        popUpTo(BottomBarScreen.Home.route) {
            inclusive = false
        }
    }
}

sealed class QuizScreen(val route: String) {
    object Quiz : QuizScreen(route = "QUIZ")
    object Result : QuizScreen(route = "RESULT")
}

sealed class AlphabetScreen(val route: String) {
    object Alphabet : AlphabetScreen(route = "ALPHABET")
}