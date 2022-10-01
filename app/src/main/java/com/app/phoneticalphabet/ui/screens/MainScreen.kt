package com.app.phoneticalphabet.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.phoneticalphabet.BottomBarScreen
import com.app.phoneticalphabet.R
import com.app.phoneticalphabet.ui.navigation.graph.MainNavGraph
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    darkTheme: Boolean,
    toggleDarkTheme: (Boolean) -> Unit,
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        topBar = {
            TopBar(
                navController = navController,
                darkTheme = darkTheme,
                toggleDarkTheme = toggleDarkTheme
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            MainNavGraph(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    darkTheme: Boolean,
    toggleDarkTheme: (Boolean) -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val visible = currentDestination?.route?.let { BottomBarScreen.Home.route != it } ?: false

    currentDestination?.route?.let {
        val title = it
            .replace("([^a-zA-Z0-9 -])".toRegex(), "-")
            .split("-")
            .first()
            .lowercase(Locale.ROOT).capitalize(Locale.ROOT)

        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (visible) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                            contentDescription = "back"
                        )
                    }
                }
            },
            actions = {
                val icon = if (darkTheme) R.drawable.sunny_48px else R.drawable.bedtime_48px
                IconButton(
                    modifier = Modifier
                        .height(24.dp)
                        .padding(horizontal = 8.dp),
                    onClick = { toggleDarkTheme(!darkTheme) }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "toggle dark theme"
                    )
                }
            },
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Stats,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
