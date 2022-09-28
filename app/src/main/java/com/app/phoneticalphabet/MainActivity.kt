package com.app.phoneticalphabet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.app.phoneticalphabet.ui.navigation.graph.RootNavigationGraph
import com.app.phoneticalphabet.ui.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme = remember { mutableStateOf(false) }

            MainTheme(
                darkTheme = darkTheme.value
            ) {
                RootNavigationGraph(
                    navController = rememberNavController(),
                    darkTheme = darkTheme.value,
                    toggleDarkTheme = { darkTheme.value = it }
                )
            }
        }
    }
}
