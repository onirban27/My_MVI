package com.imaginers.mymvi.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imaginers.mymvi.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInBtn.setOnClickListener{
            startActivity(Intent(
                this,
                LoginActivity::class.java
            ))
        }


        binding.signUpBtn.setOnClickListener{
            startActivity(Intent(
                this,
                SignupActivity::class.java
            ))
        }
    }
}