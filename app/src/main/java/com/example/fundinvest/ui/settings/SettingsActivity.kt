package com.example.fundinvest.ui.settings


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.fundinvest.MainActivity
import com.example.fundinvest.R
import com.example.fundinvest.databinding.ActivitySettingsBinding
import java.util.Locale


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }
       checkVersion()
        val sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        val nightMode = sharedPreferences.getBoolean("night",false)
        setLocale()
        if (nightMode){
            binding.dark.isChecked = true
        }else{
            binding.light.isChecked = true
        }
        binding.dark.setOnClickListener{
            if (nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night",false)
                editor.apply()
                binding.light.isChecked = true
                binding.dark.isChecked = false
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night",true)
                editor.apply()
                binding.dark.isChecked = true
                binding.light.isChecked = false
            }

        }
        binding.light.setOnClickListener{
            if (nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night",false)
                editor.apply()
                binding.light.isChecked = true
                binding.dark.isChecked = false
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night",true)
                editor.apply()
                binding.light.isChecked = false
                binding.dark.isChecked = true
            }

        }
    }
    fun setLocale(){
        val sharedPreferences1 = getSharedPreferences("Language", Context.MODE_PRIVATE)
       // val language = sharedPreferences1.getString("language", "en")
        binding.ruButton.setOnClickListener{
            Locale.setDefault(Locale("ru"))
            val editor = sharedPreferences1.edit()
            editor.putString("language", "ru")
            editor.apply()
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ru"))
        }
        binding.enButton.setOnClickListener{
            Locale.setDefault(Locale("en"))
            val editor = sharedPreferences1.edit()
            editor.putString("language", "en")
            editor.apply()
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
        }
    }

    private fun checkVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }


}
