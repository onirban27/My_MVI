package com.imaginers.mymvi.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaginers.mymvi.states.UiState
import com.imaginers.mymvi.data.db.entity.User
import com.imaginers.mymvi.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel()  {

    private val _registerUiState = MutableStateFlow<UiState>(UiState.Empty)
    val registerUiState : StateFlow<UiState> = _registerUiState

    fun register(repository: UserRepository, userName: String, password: String, passwordRe: String ) = viewModelScope.launch {
        _registerUiState.value = UiState.Loading
        delay(2000L)
        if(userName.length>2 && password.length >3 && passwordRe == password){
            insertData(repository, User(null,userName,password))
        }else{
            _registerUiState.value = UiState.Error("Wrong Data")
        }
    }

    private fun insertData(repository: UserRepository, user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.create(user)
            _registerUiState.value = UiState.Success
        }
    }

}