package com.example.fundinvest.ui.statements

import android.content.Context
import android.graphics.Color
import android.icu.util.LocaleData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundinvest.data.BalanceSheet
import com.example.fundinvest.data.CashFlow
import com.example.fundinvest.data.IncomeStatement
import com.example.fundinvest.databinding.FragmentStetementsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class StatementsFragment : Fragment() {

    private var _binding: FragmentStetementsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var incomeList = listOf<IncomeStatement>()
    private var balanceSheetList = listOf<BalanceSheet>()
    private var cashFlowList = listOf<CashFlow>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val statementsViewModel =
            ViewModelProvider(this)[StatementsViewModel::class.java]
        _binding = FragmentStetementsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.statementList.layoutManager = LinearLayoutManager(this.context)
        val adapter  = RecyclerViewAdapter(this)
        statementsViewModel.incomeStatements.observe(viewLifecycleOwner){
            incomeList = it
            if(it.isEmpty()){
                val toast = Toast.makeText(this.context, "The day limit was reached",Toast.LENGTH_LONG)
                toast.show()
            }else{
                adapter.setIncomeStatementData(incomeList)
                binding.statementList.adapter = adapter
            }
        }
        statementsViewModel.balanceSheetStatements.observe(viewLifecycleOwner){
            balanceSheetList = it
        }
        statementsViewModel.cashFlowStatements.observe(viewLifecycleOwner){
            cashFlowList = it
        }
        binding.statementSearch.setOnEditorActionListener { v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                statementsViewModel.getIncomeStatement(v.text.toString())
                statementsViewModel.getBalanceSheet(v.text.toString())
                statementsViewModel.getCashFlow(v.text.toString())
                sendDataToDb(v.text.toString())
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow( binding.statementSearch.windowToken, 0)
                true
            } else {
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow( binding.statementSearch.windowToken, 0)
                false
            }
        }
        binding.incomeCard.setOnClickListener{
            binding.cashFlowText.setBackgroundColor(Color.WHITE)
            binding.cashFlowText.setTextColor(Color.BLACK)
            binding.balanceSheetText.setBackgroundColor(Color.WHITE)
            binding.balanceSheetText.setTextColor(Color.BLACK)
            binding.incomeText.setBackgroundColor(Color.BLACK)
            binding.incomeText.setTextColor(Color.WHITE)
            adapter.setIncomeStatementData(incomeList)
            binding.statementList.adapter = adapter
        }
        binding.balanceSheetCard.setOnClickListener{
            binding.incomeText.setBackgroundColor(Color.WHITE)
            binding.incomeText.setTextColor(Color.BLACK)
            binding.cashFlowText.setBackgroundColor(Color.WHITE)
            binding.cashFlowText.setTextColor(Color.BLACK)
            binding.balanceSheetText.setBackgroundColor(Color.BLACK)
            binding.balanceSheetText.setTextColor(Color.WHITE)
            if (balanceSheetList.isNotEmpty()){
                adapter.setBalanceSheetData(balanceSheetList)
                binding.statementList.adapter = adapter
            }else{
                val toast = Toast.makeText(this.context, "The day limit was reached",Toast.LENGTH_LONG)
                toast.show()
            }
        }
        binding.cashFlowCard.setOnClickListener{
            binding.incomeText.setBackgroundColor(Color.WHITE)
            binding.incomeText.setTextColor(Color.BLACK)
            binding.balanceSheetText.setBackgroundColor(Color.WHITE)
            binding.balanceSheetText.setTextColor(Color.BLACK)
            binding.cashFlowText.setBackgroundColor(Color.BLACK)
            binding.cashFlowText.setTextColor(Color.WHITE)
            if (cashFlowList.isNotEmpty()){
                adapter.setCashFlowData(cashFlowList)
                binding.statementList.adapter = adapter
            }else{
                val toast = Toast.makeText(this.context, "The day limit was reached",Toast.LENGTH_LONG)
                toast.show()
            }
        }
        return root
    }

    private fun sendDataToDb(token:String) {
        val userInfo = hashMapOf<String,String>()
        userInfo["token"] = token
        userInfo["date"] = LocalDate.now().toString()
        FirebaseDatabase.getInstance().getReference().child("StatementsHistory").child(UUID.randomUUID().toString())
            .setValue(userInfo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}