package com.example.dinnerplanswitch.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dinnerplanswitch.ui.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    route = Screen.Detail.passArgs(
                        name = "Håkon",
                        surname = "Pettersen",
                        city = "Kløfta"
                    )
                )
            }
    ) {
        Text(text = "Home screen")
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController()
    )
}
