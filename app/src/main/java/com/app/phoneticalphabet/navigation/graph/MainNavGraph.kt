package com.app.phoneticalphabet.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.phoneticalphabet.BottomBarScreen
import com.app.phoneticalphabet.ui.screens.alphabet.AlphabetScreen
import com.app.phoneticalphabet.ui.screens.flashcard.FlashCardScreen
import com.app.phoneticalphabet.ui.screens.home.HomeScreen
import com.app.phoneticalphabet.ui.screens.quiz.QuizScreen
import com.app.phoneticalphabet.ui.screens.random.ScreenContent
import com.app.phoneticalphabet.ui.screens.result.ResultScreen
import com.app.phoneticalphabet.ui.screens.stats.StatsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    showInAppReview: () -> Unit,
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                onAlphabetClicked = { navController.fromHomeToAlphabet() },
                onFlashCardsClicked = { navController.fromHomeToFlashcard() },
                onQuizClicked = { navController.navigate(Graph.QUIZ) }
            )
        }
        composable(route = BottomBarScreen.Stats.route) {
            StatsScreen(showInAppReview)
        }
        alphabetGraph()
        flashcardGraph(navController = navController)
        quizNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.flashcardGraph(navController: NavHostController) {
    navigation(
        route = Graph.FLASHCARD,
        startDestination = FlashcardScreen.Flashcard.route,
    ) {
        composable(route = FlashcardScreen.Flashcard.route) {
            FlashCardScreen(
                onViewStats = { navController.toStats() },
                onEndFlashcards = { navController.toHome() }
            )
        }
    }
}

fun NavGraphBuilder.alphabetGraph() {
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
            QuizScreen { score, newHighScore -> navController.fromQuizToResult(score, newHighScore) }
        }
        composable(
            route = "${QuizScreen.Result.route}/{score}/{newHighScore}",
            arguments = listOf(
                navArgument("score") {
                    type = NavType.IntType
                },
                navArgument("newHighScore") {
                    type = NavType.BoolType
                }
            )
        ) {
            ResultScreen(
                onNewGame = { navController.fromResultToQuiz() },
                onViewStats = { navController.toStats() },
                onFinish = { navController.toHome() }
            )
        }
    }
}

private fun NavHostController.fromQuizToResult(score: Int, newHighScore: Boolean) {
    navigate("${QuizScreen.Result.route}/$score/$newHighScore") {
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

private fun NavHostController.toHome() {
    navigate(Graph.MAIN) {
        popUpTo(BottomBarScreen.Home.route) {
            inclusive = true
        }
    }
}

private fun NavHostController.toStats() {
    navigate(route = BottomBarScreen.Stats.route) {
        popUpTo(BottomBarScreen.Home.route) {
            inclusive = false
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

private fun NavHostController.fromHomeToFlashcard() {
    navigate(Graph.FLASHCARD) {
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

sealed class FlashcardScreen(val route: String) {
    object Flashcard : FlashcardScreen(route = "FLASHCARDS")
}
