package com.tarunmalkam.campusexpensetracker

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class ExpenseStore(context: Context) {
    private val prefs = context.getSharedPreferences("expenses_store", Context.MODE_PRIVATE)

    fun getExpenses(): MutableList<Expense> {
        val json = prefs.getString(KEY_EXPENSES, "[]") ?: "[]"
        val array = JSONArray(json)
        val expenses = mutableListOf<Expense>()

        for (index in 0 until array.length()) {
            val item = array.getJSONObject(index)
            expenses.add(
                Expense(
                    id = item.optLong("id"),
                    title = item.optString("title"),
                    amount = item.optDouble("amount"),
                    category = item.optString("category"),
                    date = item.optString("date"),
                    note = item.optString("note")
                )
            )
        }

        return expenses.sortedByDescending { it.id }.toMutableList()
    }

    fun addExpense(expense: Expense) {
        val expenses = getExpenses()
        expenses.add(0, expense)
        saveExpenses(expenses)
    }

    fun deleteExpense(id: Long) {
        val updated = getExpenses().filterNot { it.id == id }
        saveExpenses(updated)
    }

    fun getTotalSpent(): Double = getExpenses().sumOf { it.amount }

    fun getTotalForMonth(monthPrefix: String): Double {
        return getExpenses()
            .filter { it.date.startsWith(monthPrefix) }
            .sumOf { it.amount }
    }

    fun getTotalForToday(today: String): Double {
        return getExpenses()
            .filter { it.date == today }
            .sumOf { it.amount }
    }

    fun getCategoryTotals(monthPrefix: String? = null): Map<String, Double> {
        val filtered = if (monthPrefix == null) {
            getExpenses()
        } else {
            getExpenses().filter { it.date.startsWith(monthPrefix) }
        }

        return filtered
            .groupBy { it.category }
            .mapValues { entry -> entry.value.sumOf { it.amount } }
            .toList()
            .sortedByDescending { it.second }
            .toMap()
    }

    private fun saveExpenses(expenses: List<Expense>) {
        val array = JSONArray()
        expenses.forEach { expense ->
            val item = JSONObject()
            item.put("id", expense.id)
            item.put("title", expense.title)
            item.put("amount", expense.amount)
            item.put("category", expense.category)
            item.put("date", expense.date)
            item.put("note", expense.note)
            array.put(item)
        }

        prefs.edit().putString(KEY_EXPENSES, array.toString()).apply()
    }

    companion object {
        private const val KEY_EXPENSES = "expenses"
    }
}
