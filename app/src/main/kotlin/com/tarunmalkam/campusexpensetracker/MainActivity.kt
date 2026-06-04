package com.tarunmalkam.campusexpensetracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : Activity() {
    private lateinit var expenseStore: ExpenseStore
    private lateinit var budgetStore: BudgetStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        expenseStore = ExpenseStore(this)
        budgetStore = BudgetStore(this)
        render()
    }

    override fun onResume() {
        super.onResume()
        if (::expenseStore.isInitialized) {
            render()
        }
    }

    private fun render() {
        val container = Ui.screen(this)
        val today = DateUtils.today()
        val month = DateUtils.currentMonth()
        val todaySpent = expenseStore.getTotalForToday(today)
        val monthSpent = expenseStore.getTotalForMonth(month)
        val monthlyBudget = budgetStore.getMonthlyBudget()
        val remaining = monthlyBudget - monthSpent
        val categoryTotals = expenseStore.getCategoryTotals(month)
        val topCategory = categoryTotals.maxByOrNull { it.value }?.key ?: "No expenses yet"

        container.addView(Ui.title(this, "Campus Expense Tracker"))
        container.addView(Ui.subtitle(this, "Track student expenses, manage monthly budget, and understand where your money goes."))

        container.addView(dashboardCard("Spent Today", MoneyUtils.format(todaySpent), "Date: $today"))
        container.addView(dashboardCard("Spent This Month", MoneyUtils.format(monthSpent), "Month: $month"))
        container.addView(dashboardCard("Remaining Budget", MoneyUtils.format(remaining), budgetMessage(monthlyBudget, monthSpent)))
        container.addView(dashboardCard("Top Category", topCategory, "Highest spending category this month"))

        container.addView(Ui.sectionTitle(this, "Quick Actions"))
        container.addView(Ui.button(this, "Add Expense") {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        })
        container.addView(Ui.outlineButton(this, "View Expenses") {
            startActivity(Intent(this, ExpenseListActivity::class.java))
        })
        container.addView(Ui.outlineButton(this, "Set Monthly Budget") {
            startActivity(Intent(this, BudgetActivity::class.java))
        })
        container.addView(Ui.outlineButton(this, "View Summary") {
            startActivity(Intent(this, SummaryActivity::class.java))
        })

        container.addView(Ui.divider(this))
        container.addView(Ui.muted(this, "Made for students who want to control daily spending."))
    }

    private fun dashboardCard(title: String, value: String, info: String): LinearLayout {
        val card = Ui.card(this)
        val titleView = Ui.muted(this, title)
        val valueView = Ui.title(this, value)
        valueView.textSize = 22f
        val infoView = Ui.muted(this, info)
        card.addView(titleView)
        card.addView(valueView)
        card.addView(infoView)
        return card
    }

    private fun budgetMessage(budget: Double, spent: Double): String {
        return when {
            budget <= 0.0 -> "Set a monthly budget to track balance"
            spent > budget -> "Budget exceeded by ${MoneyUtils.format(spent - budget)}"
            else -> "Monthly budget: ${MoneyUtils.format(budget)}"
        }
    }
}
