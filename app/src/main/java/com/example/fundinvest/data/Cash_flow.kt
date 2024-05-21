package com.example.fundinvest.data

import com.google.gson.annotations.SerializedName

data class CashFlowStatementsData(
    @field:SerializedName("symbol")
    val symbol: String,
    @field:SerializedName("annualReports")
    val annualReports: List<CashFlow>
)
data class CashFlow(
    val fiscalDateEnding: String,
    val reportedCurrency: String,
    val operatingCashflow: String,
    val paymentsForOperatingActivities: String,
    val proceedsFromOperatingActivities: String?,
    val changeInOperatingLiabilities: String,
    val changeInOperatingAssets: String,
    val depreciationDepletionAndAmortization: String,
    val capitalExpenditures: String,
    val changeInReceivables: String,
    val changeInInventory: String,
    val profitLoss: String,
    val cashflowFromInvestment: String,
    val cashflowFromFinancing: String,
    val proceedsFromRepaymentsOfShortTermDebt: String?,
    val paymentsForRepurchaseOfCommonStock: String?,
    val paymentsForRepurchaseOfEquity: String?,
    val paymentsForRepurchaseOfPreferredStock: String?,
    val dividendPayout: String?,
    val dividendPayoutCommonStock: String?,
    val dividendPayoutPreferredStock: String?,
    val proceedsFromIssuanceOfCommonStock: String?,
    val proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet: String?,
    val proceedsFromIssuanceOfPreferredStock: String?,
    val proceedsFromRepurchaseOfEquity: String?,
    val proceedsFromSaleOfTreasuryStock: String?,
    val changeInCashAndCashEquivalents: String?,
    val changeInExchangeRate: String?,
    val netIncome: String
)