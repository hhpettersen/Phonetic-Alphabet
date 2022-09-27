package com.app.phoneticalphabet.ui.screens.result

import androidx.annotation.DrawableRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.app.phoneticalphabet.models.ScoreTier
import com.app.phoneticalphabet.models.ScoreTier.Companion.medalTint
import com.app.phoneticalphabet.models.ScoreTier.Companion.tierText
import com.app.phoneticalphabet.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ResultViewState(
    val score: Int = 0,
    @DrawableRes val medalTint: Int = 0,
    val tierText: String = "",
)

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: Repository,
    handle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(ResultViewState())
    val state: StateFlow<ResultViewState> = _state

    init {
        val score = handle["score"] ?: 0
        val tier = ScoreTier.getTier(score)

        _state.update {
            it.copy(
                score = score,
                medalTint = tier.medalTint(),
                tierText = tier.tierText()
            )
        }
    }
}