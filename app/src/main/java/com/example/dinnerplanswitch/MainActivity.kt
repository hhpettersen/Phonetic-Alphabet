package com.example.dinnerplanswitch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.dinnerplanswitch.ui.navigation.nav_graph.RootNavigationGraph
import com.example.dinnerplanswitch.ui.theme.DinnerPlanSwitchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinnerPlanSwitchTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
