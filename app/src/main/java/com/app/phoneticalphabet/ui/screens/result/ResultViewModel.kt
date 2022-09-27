package com.app.phoneticalphabet.ui.screens.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.app.phoneticalphabet.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: Repository,
    handle: SavedStateHandle,
): ViewModel() {
    val score = handle["score"] ?: 0
}