package com.example.fundinvest.ui.statements

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fundinvest.databinding.FragmentStetementsBinding

class StatementsFragment : Fragment() {

    private var _binding: FragmentStetementsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val statementsViewModel =
            ViewModelProvider(this).get(StatementsViewModel::class.java)

        _binding = FragmentStetementsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.incomeCard.setOnClickListener{
            binding.cashFlowText.setBackgroundColor(Color.WHITE)
            binding.cashFlowText.setTextColor(Color.BLACK)
            binding.balanceSheetText.setBackgroundColor(Color.WHITE)
            binding.balanceSheetText.setTextColor(Color.BLACK)
            binding.incomeText.setBackgroundColor(Color.BLACK)
            binding.incomeText.setTextColor(Color.WHITE)
        }
        binding.cashFlowCard.setOnClickListener{
            binding.incomeText.setBackgroundColor(Color.WHITE)
            binding.incomeText.setTextColor(Color.BLACK)
            binding.balanceSheetText.setBackgroundColor(Color.WHITE)
            binding.balanceSheetText.setTextColor(Color.BLACK)
            binding.cashFlowText.setBackgroundColor(Color.BLACK)
            binding.cashFlowText.setTextColor(Color.WHITE)
        }
        binding.balanceSheetCard.setOnClickListener{
            binding.incomeText.setBackgroundColor(Color.WHITE)
            binding.incomeText.setTextColor(Color.BLACK)
            binding.cashFlowText.setBackgroundColor(Color.WHITE)
            binding.cashFlowText.setTextColor(Color.BLACK)
            binding.balanceSheetText.setBackgroundColor(Color.BLACK)
            binding.balanceSheetText.setTextColor(Color.WHITE)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}