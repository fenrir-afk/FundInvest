package com.example.fundinvest.ui.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.fundinvest.R
import com.example.fundinvest.data.IncomeStatement

class RecyclerViewAdapter(private val viewModelStoreOwner: ViewModelStoreOwner)
    : RecyclerView.Adapter<RecyclerViewAdapter.TradeViewHolder>() {

    private var incomeStatementList: List<IncomeStatement> = listOf()
    private val token = 0

    class TradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)!!
        val text1Row1 = itemView.findViewById<TextView>(R.id.row1_text1)!!
        val text2Row1 = itemView.findViewById<TextView>(R.id.row1_text2)!!
        val text1Row2 = itemView.findViewById<TextView>(R.id.row2_text1)!!
        val text2Row2 = itemView.findViewById<TextView>(R.id.row2_text2)!!
        val text1Row3 = itemView.findViewById<TextView>(R.id.row3_text1)!!
        val text2Row3 = itemView.findViewById<TextView>(R.id.row3_text2)!!
        val text1Row4 = itemView.findViewById<TextView>(R.id.row4_text1)!!
        val text2Row4 = itemView.findViewById<TextView>(R.id.row4_text2)!!
        val text1Row5 = itemView.findViewById<TextView>(R.id.row5_text1)!!
        val text2Row5 = itemView.findViewById<TextView>(R.id.row5_text2)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.statement_layout,
            parent,
            false
        )
        return TradeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TradeViewHolder, position: Int) {
        if (token == 0){
            holder.apply {
                title.text = "Date: " +  incomeStatementList[position].fiscalDateEnding

                text1Row1.text = "Total Revenue"
                text2Row1.text = incomeStatementList[position].totalRevenue

                text1Row2.text = "Gross Profit"
                text2Row2.text = incomeStatementList[position].grossProfit

                text1Row3.text = "Operating Income"
                text2Row3.text = incomeStatementList[position].operatingIncome

                text1Row4.text = "Com.Income Net of Tax"
                text2Row4.text = incomeStatementList[position].comprehensiveIncomeNetOfTax

                text1Row5.text = "Net Income"
                text2Row5.text = incomeStatementList[position].netIncome
            }
        }
    }
    fun setIncomeStatementData(incomeStatements: List<IncomeStatement>,token:Int = 0) {
        incomeStatementList = incomeStatements
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = incomeStatementList.size
}