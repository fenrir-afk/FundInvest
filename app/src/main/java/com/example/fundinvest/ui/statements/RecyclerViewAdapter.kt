package com.example.fundinvest.ui.statements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.fundinvest.R
import com.example.fundinvest.data.BalanceSheet
import com.example.fundinvest.data.CashFlow
import com.example.fundinvest.data.IncomeStatement

class RecyclerViewAdapter(private val viewModelStoreOwner: ViewModelStoreOwner)
    : RecyclerView.Adapter<RecyclerViewAdapter.TradeViewHolder>() {

    private var incomeStatementList: List<IncomeStatement> = listOf()
    private var balanceSheetStatementList: List<BalanceSheet> = listOf()
    private var cashFlowStatementList: List<CashFlow> = listOf()
    private var tokenGlobal = 0

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
        if (tokenGlobal == 0){
            holder.apply {
                title.text = "Date: " +  incomeStatementList[position].fiscalDateEnding

                text1Row1.text = "Total Revenue"
                text2Row1.text = incomeStatementList[position].totalRevenue

                text1Row2.text = "Gross Profit"
                text2Row2.text = incomeStatementList[position].grossProfit

                text1Row3.text = "Operating Income"
                text2Row3.text = incomeStatementList[position].operatingIncome

                text1Row4.text = "Comprehensive Income\nNet Of Tax"
                text2Row4.text = incomeStatementList[position].comprehensiveIncomeNetOfTax

                text1Row5.text = "Net Income"
                text2Row5.text = incomeStatementList[position].netIncome
            }
        }
        if (tokenGlobal == 1){
            holder.apply {
                title.text = "Date: " +  balanceSheetStatementList[position].fiscalDateEnding

                text1Row1.text = "Total Assets"
                text2Row1.text = balanceSheetStatementList[position].totalAssets

                text1Row2.text = "Total Liabilities"
                text2Row2.text = balanceSheetStatementList[position].totalLiabilities

                text1Row3.text = "Total ShareHold\nEquity"
                text2Row3.text = balanceSheetStatementList[position].totalShareholderEquity

                text1Row4.text = "Cash And Cash\nEquivalents"
                text2Row4.text = balanceSheetStatementList[position].cashAndCashEquivalentsAtCarryingValue

                text1Row5.text = "Goodwill"
                text2Row5.text = balanceSheetStatementList[position].goodwill
            }
        }
        if (tokenGlobal == 2){
            holder.apply {
                title.text = "Date: " +  cashFlowStatementList[position].fiscalDateEnding

                text1Row1.text = "Operating Cash Flow"
                text2Row1.text = cashFlowStatementList[position].operatingCashflow

                text1Row2.text = "Amortization"
                text2Row2.text = cashFlowStatementList[position].depreciationDepletionAndAmortization

                text1Row3.text = "Profit/loss"
                text2Row3.text = cashFlowStatementList[position].profitLoss

                text1Row4.text = "Cash Flow from\nInvestors"
                text2Row4.text = cashFlowStatementList[position].cashflowFromInvestment

                text1Row5.text = "Net Income"
                text2Row5.text = cashFlowStatementList[position].netIncome
            }
        }
    }
    fun setIncomeStatementData(income: List<IncomeStatement>,token:Int = 0) {
        incomeStatementList = income
        tokenGlobal = token
        notifyDataSetChanged()
    }
    fun setBalanceSheetData(balanceSheet: List<BalanceSheet>,token:Int = 1) {
        balanceSheetStatementList = balanceSheet
        tokenGlobal = token
        notifyDataSetChanged()
    }
    fun setCashFlowData(cashFlow: List<CashFlow>,token:Int = 2) {
        cashFlowStatementList = cashFlow
        tokenGlobal = token
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = incomeStatementList.size
}