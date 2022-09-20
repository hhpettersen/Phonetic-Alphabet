package com.example.dinnerplanswitch.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dinnerplanswitch.ui.navigation.AUTH_ROUTE
import com.example.dinnerplanswitch.ui.navigation.Screen

@Composable
fun DetailScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
//                navController.popBackStack()
                navController.navigate(
                    route = AUTH_ROUTE
                )
            }
    ) {
        Text(text = "Detail screen")
    }
}