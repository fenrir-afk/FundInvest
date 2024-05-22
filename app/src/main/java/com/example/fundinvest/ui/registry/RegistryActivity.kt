package com.example.fundinvest.ui.registry

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fundinvest.MainActivity
import com.example.fundinvest.databinding.ActivityRegistryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener {
            if (binding.email.text.toString().isEmpty() || binding.password.text.toString().isEmpty()
                || binding.userName.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Fields can not be empty", Toast.LENGTH_LONG).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.email.text.toString(), binding.password.text.toString())
                    .addOnCompleteListener{
                    if (it.isSuccessful) {
                        var userInfo = hashMapOf<String,String>()
                        userInfo["email"] = binding.email.text.toString()
                        userInfo["password"] = binding.password.text.toString()
                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .setValue(userInfo)
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }

    }
}