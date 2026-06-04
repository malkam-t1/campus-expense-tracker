package com.tarunmalkam.campusexpensetracker

import android.content.Context

class BudgetStore(context: Context) {
    private val prefs = context.getSharedPreferences("budget_store", Context.MODE_PRIVATE)

    fun getMonthlyBudget(): Double {
        return prefs.getFloat(KEY_BUDGET, 0f).toDouble()
    }

    fun saveMonthlyBudget(amount: Double) {
        prefs.edit().putFloat(KEY_BUDGET, amount.toFloat()).apply()
    }

    companion object {
        private const val KEY_BUDGET = "monthly_budget"
    }
}
