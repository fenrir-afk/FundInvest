package com.example.fundinvest.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.fundinvest.MainActivity
import com.example.fundinvest.R
import com.example.fundinvest.databinding.ActivityLoginBinding
import com.example.fundinvest.ui.registry.RegistryActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginButton.setOnClickListener {
            if (binding.email.text.toString().isEmpty() || binding.password.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Fields can not be empty", Toast.LENGTH_LONG).show()
            } else {
                loginViewModel.login(binding.email.text.toString(),binding.password.text.toString())
                loginViewModel.loginResult.observe(this){
                    if (it){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"LoginError",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.sendToSighUp.setOnClickListener {
            startActivity(Intent(this, RegistryActivity::class.java))
        }
    }
}