package com.tarunmalkam.campusexpensetracker

import android.app.Activity
import android.os.Bundle
import android.text.InputType

class BudgetActivity : Activity() {
    private lateinit var budgetStore: BudgetStore
    private lateinit var expenseStore: ExpenseStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        budgetStore = BudgetStore(this)
        expenseStore = ExpenseStore(this)
        render()
    }

    private fun render() {
        val container = Ui.screen(this)
        val currentMonth = DateUtils.currentMonth()
        val budget = budgetStore.getMonthlyBudget()
        val spent = expenseStore.getTotalForMonth(currentMonth)
        val remaining = budget - spent

        container.addView(Ui.title(this, "Monthly Budget"))
        container.addView(Ui.subtitle(this, "Set your monthly limit and track remaining money."))

        val summaryCard = Ui.card(this)
        summaryCard.addView(Ui.body(this, "Month: $currentMonth"))
        summaryCard.addView(Ui.body(this, "Budget: ${MoneyUtils.format(budget)}"))
        summaryCard.addView(Ui.body(this, "Spent: ${MoneyUtils.format(spent)}"))
        summaryCard.addView(Ui.body(this, "Remaining: ${MoneyUtils.format(remaining)}"))
        summaryCard.addView(
            Ui.muted(
                this,
                if (budget > 0 && spent > budget) "Warning: You crossed your budget." else "Keep tracking your daily spending."
            )
        )
        container.addView(summaryCard)

        container.addView(Ui.sectionTitle(this, "Set Budget"))
        val budgetInput = Ui.input(this, "Monthly budget in ₹")
        budgetInput.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        if (budget > 0) {
            budgetInput.setText(budget.toString())
        }
        container.addView(budgetInput)

        container.addView(Ui.button(this, "Save Budget") {
            val amount = budgetInput.text.toString().trim().toDoubleOrNull()
            if (amount == null || amount <= 0.0) {
                Ui.toast(this, "Enter a valid budget amount")
            } else {
                budgetStore.saveMonthlyBudget(amount)
                Ui.toast(this, "Budget saved")
                render()
            }
        })

        container.addView(Ui.outlineButton(this, "Back") { finish() })
    }
}
