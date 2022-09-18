package com.example.dinnerplanswitch.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dinnerplanswitch.ui.composables.BaseScreen

@Composable
fun SignUpScreen(
    navController: NavController,
) {
    BaseScreen {
        Text(text = "Sign up")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Button")
        }
    }
}
