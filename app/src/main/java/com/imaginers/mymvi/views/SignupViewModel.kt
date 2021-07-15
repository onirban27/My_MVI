package com.imaginers.mymvi.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaginers.mymvi.states.UiState
import com.imaginers.mymvi.data.db.entity.User
import com.imaginers.mymvi.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(repository:UserRepository) : ViewModel()  {

    var userRepository = repository
    private val _registerUiState = MutableStateFlow<UiState>(UiState.Empty)
    val registerUiState : StateFlow<UiState> = _registerUiState

    fun register(userName: String, password: String, passwordRe: String ) = viewModelScope.launch {
        _registerUiState.value = UiState.Loading
        delay(2000L)
        if(userName.length>2 && password.length >3 && passwordRe == password){
            insertData(User(null,userName,password))
        }else{
            _registerUiState.value = UiState.Error("Wrong Data")
        }
    }

    private fun insertData(user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            userRepository.create(user)
            _registerUiState.value = UiState.Success
        }
    }

}