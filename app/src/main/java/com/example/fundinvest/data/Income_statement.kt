package com.example.fundinvest.data

import com.google.gson.annotations.SerializedName

data class IncomeStatementsData(
    @field:SerializedName("symbol")
    val symbol: String,
    @field:SerializedName("annualReports")
    val annualReports: List<IncomeStatement>
)

data class IncomeStatement(
    @field:SerializedName("fiscalDateEnding")
    val fiscalDateEnding: String,
    @field:SerializedName("reportedCurrency")
    val reportedCurrency: String,
    @field:SerializedName("grossProfit")
    val grossProfit: String,
    @field:SerializedName("totalRevenue")
    val totalRevenue: String,
    @field:SerializedName("costOfRevenue")
    val costOfRevenue: String,
    @field:SerializedName("costofGoodsAndServicesSold")
    val costofGoodsAndServicesSold: String,
    @field:SerializedName("operatingIncome")
    val operatingIncome: String,
    @field:SerializedName("sellingGeneralAndAdministrative")
    val sellingGeneralAndAdministrative: String,
    @field:SerializedName("researchAndDevelopment")
    val researchAndDevelopment: String,
    @field:SerializedName("operatingExpenses")
    val operatingExpenses: String,
    @field:SerializedName("investmentIncomeNet")
    val investmentIncomeNet: String,
    @field:SerializedName("netInterestIncome")
    val netInterestIncome: String,
    @field:SerializedName("interestIncome")
    val interestIncome: String,
    @field:SerializedName("interestExpense")
    val interestExpense: String,
    @field:SerializedName("nonInterestIncome")
    val nonInterestIncome: String,
    @field:SerializedName("otherNonOperatingIncome")
    val otherNonOperatingIncome: String,
    @field:SerializedName("depreciation")
    val depreciation: String,
    @field:SerializedName("depreciationAndAmortization")
    val depreciationAndAmortization: String,
    @field:SerializedName("incomeBeforeTax")
    val incomeBeforeTax: String,
    @field:SerializedName("incomeTaxExpense")
    val incomeTaxExpense: String,
    @field:SerializedName("interestAndDebtExpense")
    val interestAndDebtExpense: String,
    @field:SerializedName("netIncomeFromContinuingOperations")
    val netIncomeFromContinuingOperations: String,
    @field:SerializedName("comprehensiveIncomeNetOfTax")
    val comprehensiveIncomeNetOfTax: String,
    @field:SerializedName("ebit")
    val ebit: String,
    @field:SerializedName("ebitda")
    val ebitda: String,
    @field:SerializedName("netIncome")
    val netIncome: String
)