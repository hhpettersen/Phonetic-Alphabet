package com.example.dinnerplanswitch.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dinnerplanswitch.ui.composables.BaseScreen

@Composable
fun LoginScreen(
    navController: NavController,
) {
    BaseScreen {
        Text(text = "Login")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Back")
        }
    }
}
