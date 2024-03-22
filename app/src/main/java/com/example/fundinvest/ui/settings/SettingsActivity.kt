package com.example.fundinvest.ui.settings

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.fundinvest.R
import com.example.fundinvest.databinding.ActivitySettingsBinding


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        var list = arrayListOf<String>("Russian","English")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, com.example.fundinvest.R.layout.spinner_item, list)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.spinner.adapter = adapter;
    }
}