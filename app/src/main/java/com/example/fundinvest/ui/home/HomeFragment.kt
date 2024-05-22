package com.example.fundinvest.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fundinvest.databinding.FragmentHomeBinding
import com.example.fundinvest.ui.ipo.IpoActivity
import com.example.fundinvest.ui.settings.SettingsActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        homeViewModel.getData()
        binding.ipoCard.setOnClickListener{
            val intent = Intent(this.context, IpoActivity::class.java)
            startActivity(intent)
        }
        binding.settingsCard.setOnClickListener{
            val intent = Intent(this.context, SettingsActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}