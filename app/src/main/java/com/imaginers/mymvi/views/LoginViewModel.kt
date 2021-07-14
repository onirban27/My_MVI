package com.imaginers.mymvi.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaginers.mymvi.App.Companion.appContext
import com.imaginers.mymvi.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _loginUiState = MutableStateFlow<UiState>(UiState.Empty)
    val loginUiState : StateFlow<UiState> = _loginUiState

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginUiState.value = UiState.Loading

        try {
            val loginResponse = appContext.repository.loginUser(username, password)

            loginResponse.collect { user->
                if (user == null) {
                    _loginUiState.value = UiState.Error("Wrong Credentials")
                    return@collect
                } else {
                    _loginUiState.value = UiState.SuccessWithMsg("Welcome, ${user.userName}")
                    return@collect
                }
            }

        } catch (e: Exception) {
            _loginUiState.value = UiState.Error(e.message!!)
            return@launch
        }
    }
}
