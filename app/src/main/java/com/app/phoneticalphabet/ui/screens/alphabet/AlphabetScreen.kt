package com.app.phoneticalphabet.ui.screens.alphabet

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.app.phoneticalphabet.firebase.FirebaseEvent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun AlphabetScreen() {
    val viewModel = hiltViewModel<AlphabetViewModel>()

    viewModel.logEvent()
    AlphabetContent()
}

@HiltViewModel
class AlphabetViewModel @Inject constructor(
    private val analytics: FirebaseAnalytics,
) : ViewModel() {
    fun logEvent() {
        analytics.logEvent(FirebaseEvent.ALPHABET_VIEWED) {}
    }
}
