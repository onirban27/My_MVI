package com.imaginers.mymvi.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.imaginers.mymvi.databinding.ActivitySignupBinding
import com.imaginers.mymvi.states.UiState
import com.imaginers.mymvi.util.hideKeyboard
import com.imaginers.mymvi.util.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener{
            hideKeyboard()
            viewModel.register(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString(),
                binding.etPasswordRetype.text.toString()
            )
        }

        binding.backBtn.setOnClickListener{
            finish()
        }

        // TODO: 7/14/2021 need to validate the fields
        lifecycleScope.launchWhenStarted {

            viewModel.registerUiState.collect{

                when(it){

                    is UiState.Success ->{

                        binding.etUsername.snack("Successfully registered",Snackbar.LENGTH_SHORT)
                        binding.progressBar.isVisible = false

                        val loginIntent  = Intent(this@SignupActivity,LoginActivity::class.java)
                        loginIntent.putExtra(USER_NAME_KEY,binding.etUsername.text.toString())
                        loginIntent.putExtra(USER_PASSWORD_KEY,binding.etPassword.text.toString())
                        startActivity(loginIntent)
                        finish()
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

    companion object{
        val USER_NAME_KEY = "user_name"
        val USER_PASSWORD_KEY = "password"
    }
}