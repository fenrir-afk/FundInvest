package com.example.fundinvest.ui.ipo

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundinvest.databinding.ActivityIpoBinding

class IpoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIpoBinding
    private lateinit var ipoViewModel: IpoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIpoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ipoViewModel = ViewModelProvider(this)[IpoViewModel::class.java]
        ipoViewModel.getIpoCompanies()
        var adapter = IpoAdapter(this)
        binding.ipoList.layoutManager = LinearLayoutManager(this)
        ipoViewModel.ipoList.observe(this){
            adapter.setIpoList(it)
            binding.ipoList.adapter = adapter
        }
        binding.backButton.setOnClickListener {
            finish()
        }

    }

}