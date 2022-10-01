package com.app.phoneticalphabet

import android.content.Context
import android.content.SharedPreferences
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
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        setContent {
            val isDarkTheme = remember { mutableStateOf(sharedPref.isDarkTheme()) }
            MainTheme(
                darkTheme = isDarkTheme.value
            ) {
                RootNavigationGraph(
                    navController = rememberNavController(),
                    darkTheme = isDarkTheme.value,
                    toggleDarkTheme = {
                        isDarkTheme.value = it
                        sharedPref.saveDarkThemePreference(it)
                    }
                )
            }
        }
    }

    private fun SharedPreferences.isDarkTheme() = getBoolean("isDarkTheme", false)
    private fun SharedPreferences.saveDarkThemePreference(value: Boolean) =
        edit().putBoolean("isDarkTheme", value).apply()
}
