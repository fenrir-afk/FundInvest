package com.example.fundinvest

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fundinvest.databinding.ActivityMainBinding
import com.example.fundinvest.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        val nightMode = sharedPreferences.getBoolean("night",false)
        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        val current = Locale.getDefault().language
        super.onCreate(savedInstanceState)
        val sharedPreferences1 = getSharedPreferences("Language", Context.MODE_PRIVATE)
        val language = sharedPreferences1.getString("language", "en")
        if (current != language){
            val newLocale = Locale(language)
            Locale.setDefault(newLocale)
            val resources = resources
            val configuration = resources.configuration
            configuration.setLocale(newLocale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            recreate()
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Получение сохраненной темы
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

}