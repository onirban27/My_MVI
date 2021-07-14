package com.imaginers.mymvi.states

sealed class UiState{
    object  Success : UiState()
    data class SuccessWithMsg(val message: String): UiState()
    data class Error(val message: String) : UiState()
    object Loading : UiState()
    object Empty : UiState()
}
