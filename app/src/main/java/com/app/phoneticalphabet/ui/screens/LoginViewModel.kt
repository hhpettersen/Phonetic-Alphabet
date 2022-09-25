package com.app.phoneticalphabet.ui.screens

import androidx.lifecycle.ViewModel
import com.app.phoneticalphabet.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    val test = "HÅKON"
}
