package com.tarunmalkam.campusexpensetracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout

class SummaryActivity : Activity() {
    private lateinit var expenseStore: ExpenseStore
    private lateinit var budgetStore: BudgetStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        expenseStore = ExpenseStore(this)
        budgetStore = BudgetStore(this)
        render()
    }

    private fun render() {
        val container = Ui.screen(this)
        val month = DateUtils.currentMonth()
        val expenses = expenseStore.getExpenses()
        val monthSpent = expenseStore.getTotalForMonth(month)
        val totalSpent = expenseStore.getTotalSpent()
        val budget = budgetStore.getMonthlyBudget()
        val remaining = budget - monthSpent
        val categoryTotals = expenseStore.getCategoryTotals(month)

        container.addView(Ui.title(this, "Spending Summary"))
        container.addView(Ui.subtitle(this, "Understand your monthly and category-wise expenses."))

        val overview = Ui.card(this)
        overview.addView(Ui.body(this, "Total Expenses Added: ${expenses.size}"))
        overview.addView(Ui.body(this, "Total Spent Overall: ${MoneyUtils.format(totalSpent)}"))
        overview.addView(Ui.body(this, "Spent This Month: ${MoneyUtils.format(monthSpent)}"))
        overview.addView(Ui.body(this, "Monthly Budget: ${MoneyUtils.format(budget)}"))
        overview.addView(Ui.body(this, "Remaining This Month: ${MoneyUtils.format(remaining)}"))
        container.addView(overview)

        container.addView(Ui.sectionTitle(this, "Category Breakdown"))
        if (categoryTotals.isEmpty()) {
            container.addView(Ui.muted(this, "No expenses added this month."))
        } else {
            categoryTotals.forEach { (category, amount) ->
                container.addView(categoryCard(category, amount, monthSpent))
            }
        }

        container.addView(Ui.button(this, "Share Summary") {
            shareSummary()
        })
        container.addView(Ui.outlineButton(this, "Back") { finish() })
    }

    private fun categoryCard(category: String, amount: Double, total: Double): LinearLayout {
        val percentage = if (total <= 0.0) 0 else ((amount / total) * 100).toInt()
        val card = Ui.card(this)
        card.addView(Ui.sectionTitle(this, category))
        card.addView(Ui.body(this, MoneyUtils.format(amount)))
        card.addView(Ui.muted(this, "$percentage% of this month's spending"))
        return card
    }

    private fun shareSummary() {
        val month = DateUtils.currentMonth()
        val monthSpent = expenseStore.getTotalForMonth(month)
        val budget = budgetStore.getMonthlyBudget()
        val remaining = budget - monthSpent
        val categoryTotals = expenseStore.getCategoryTotals(month)

        val summary = buildString {
            appendLine("Campus Expense Tracker Summary")
            appendLine("Month: $month")
            appendLine("Budget: ${MoneyUtils.format(budget)}")
            appendLine("Spent: ${MoneyUtils.format(monthSpent)}")
            appendLine("Remaining: ${MoneyUtils.format(remaining)}")
            appendLine()
            appendLine("Category Breakdown:")
            if (categoryTotals.isEmpty()) {
                appendLine("No expenses added this month.")
            } else {
                categoryTotals.forEach { (category, amount) ->
                    appendLine("- $category: ${MoneyUtils.format(amount)}")
                }
            }
        }

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Campus Expense Summary")
            putExtra(Intent.EXTRA_TEXT, summary)
        }
        startActivity(Intent.createChooser(intent, "Share Summary"))
    }
}
