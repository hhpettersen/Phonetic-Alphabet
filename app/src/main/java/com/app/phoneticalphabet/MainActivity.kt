package com.app.phoneticalphabet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.app.phoneticalphabet.navigation.graph.RootNavigationGraph
import com.app.phoneticalphabet.ui.theme.MainTheme
import com.google.android.play.core.review.ReviewManagerFactory
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
                    },
                    showInAppReview = { inAppReview() }
                )
            }
        }
    }

    private fun SharedPreferences.isDarkTheme() = getBoolean("isDarkTheme", false)
    private fun SharedPreferences.saveDarkThemePreference(value: Boolean) =
        edit().putBoolean("isDarkTheme", value).apply()

    private fun inAppReview() {
        val manager = ReviewManagerFactory.create(this)

        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = task.result
                val flow = manager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener { _ ->
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                }
            }
        }
    }
}
