package com.app.phoneticalphabet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.app.phoneticalphabet.ui.navigation.graph.RootNavigationGraph
import com.app.phoneticalphabet.ui.theme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
