package com.example.dinnerplanswitch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dinnerplanswitch.ui.navigation.nav_graph.SetupNavGraph
import com.example.dinnerplanswitch.ui.theme.DinnerPlanSwitchTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinnerPlanSwitchTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
