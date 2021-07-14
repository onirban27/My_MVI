package com.imaginers.mymvi.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.imaginers.mymvi.databinding.ActivityLoginBinding
import com.imaginers.mymvi.states.UiState
import com.imaginers.mymvi.util.snack
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent!=null){
           val username = intent.getStringExtra(SignupActivity.USER_NAME_KEY).toString()
            val password = intent.getStringExtra(SignupActivity.USER_PASSWORD_KEY).toString()

            binding.etUsername.setText(username)
            binding.etPassword.setText(password)
        }

        binding.btnLogin.setOnClickListener{
            loginViewModel.login(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }


        binding.backBtn.setOnClickListener{
            finish()
        }

        // TODO: 7/14/2021 need to validate the fields
        lifecycleScope.launchWhenStarted {

            loginViewModel.loginUiState.collect{

                when(it){

                    is UiState.Success ->{
                        binding.etUsername.snack("Successfully logged in",Snackbar.LENGTH_SHORT)
                        binding.progressBar.isVisible = false
                    }
                    is UiState.SuccessWithMsg ->{
                        binding.etUsername.snack(it.message,Snackbar.LENGTH_SHORT)
                        binding.progressBar.isVisible = false
                    }
                    is UiState.Error ->{
                        binding.etUsername.snack(it.message,Snackbar.LENGTH_SHORT)
                        binding.progressBar.isVisible = false
                    }
                    is UiState.Loading ->{
                        binding.progressBar.isVisible = true
                    }

                    else -> Unit
                }

            }
        }


    }



}