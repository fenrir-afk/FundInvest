package com.example.fundinvest.data

import com.google.gson.annotations.SerializedName

data class BalanceSheetStatementsData(
    @field:SerializedName("symbol")
    val symbol: String,
    @field:SerializedName("annualReports")
    val annualReports: List<BalanceSheet>
)
data class BalanceSheet(
    val fiscalDateEnding: String,
    val reportedCurrency: String,
    val totalAssets: String,
    val totalCurrentAssets: String,
    val cashAndCashEquivalentsAtCarryingValue: String,
    val cashAndShortTermInvestments: String,
    val inventory: String,
    val currentNetReceivables: String,
    val totalNonCurrentAssets: String,
    val propertyPlantEquipment: String,
    val accumulatedDepreciationAmortizationPPE: String,
    val intangibleAssets: String,
    val intangibleAssetsExcludingGoodwill: String,
    val goodwill: String,
    val investments: String,
    val longTermInvestments: String?,
    val shortTermInvestments: String,
    val otherCurrentAssets: String,
    val otherNonCurrentAssets: String,
    val totalLiabilities: String,
    val totalCurrentLiabilities: String,
    val currentAccountsPayable: String,
    val deferredRevenue: String,
    val currentDebt: String,
    val shortTermDebt: String,
    val totalNonCurrentLiabilities: String,
    val capitalLeaseObligations: String,
    val longTermDebt: String,
    val currentLongTermDebt: String,
    val longTermDebtNoncurrent: String?,
    val shortLongTermDebtTotal: String,
    val otherCurrentLiabilities: String,
    val otherNonCurrentLiabilities: String,
    val totalShareholderEquity: String,
    val treasuryStock: String?,
    val retainedEarnings: String,
    val commonStock: String,
    val commonStockSharesOutstanding: String
)