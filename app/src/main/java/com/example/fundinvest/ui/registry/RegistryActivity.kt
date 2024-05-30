package com.example.fundinvest.ui.registry

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fundinvest.MainActivity
import com.example.fundinvest.databinding.ActivityRegistryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistryBinding
    private lateinit var registryViewModel: RegistryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registryViewModel = ViewModelProvider(this)[RegistryViewModel::class.java]
        binding.registerButton.setOnClickListener {
            if (binding.email.text.toString().isEmpty() || binding.password.text.toString().isEmpty()
                || binding.userName.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Fields can not be empty", Toast.LENGTH_LONG).show()
            } else {
                registryViewModel.registry(binding.email.text.toString(),binding.password.text.toString())
                registryViewModel.regResult.observe(this){
                    if (it){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Log.e("Registry","Registry error")
                    }
                }
            }
        }

    }
}