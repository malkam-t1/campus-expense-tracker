package com.tarunmalkam.campusexpensetracker

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner

class ExpenseListActivity : Activity() {
    private lateinit var expenseStore: ExpenseStore
    private lateinit var container: LinearLayout
    private lateinit var filterSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        expenseStore = ExpenseStore(this)
        render()
    }

    private fun render() {
        container = Ui.screen(this)
        container.addView(Ui.title(this, "Expenses"))
        container.addView(Ui.subtitle(this, "View, filter, share, and delete saved expenses."))

        container.addView(Ui.button(this, "Add New Expense") {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        })

        container.addView(Ui.outlineButton(this, "Share Expense Report") {
            shareReport()
        })

        filterSpinner = Spinner(this)
        val filters = listOf("All Categories") + Category.all
        filterSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            filters
        )

        container.addView(Ui.sectionTitle(this, "Filter"))
        container.addView(filterSpinner)
        container.addView(Ui.outlineButton(this, "Apply Filter") {
            showExpenses()
        })

        container.addView(Ui.divider(this))
        showExpenses()
    }

    override fun onResume() {
        super.onResume()
        if (::expenseStore.isInitialized) {
            render()
        }
    }

    private fun showExpenses() {
        val selectedFilter = filterSpinner.selectedItem?.toString() ?: "All Categories"
        val allExpenses = expenseStore.getExpenses()
        val filteredExpenses = if (selectedFilter == "All Categories") {
            allExpenses
        } else {
            allExpenses.filter { it.category == selectedFilter }
        }

        while (container.childCount > LIST_START_INDEX) {
            container.removeViewAt(LIST_START_INDEX)
        }

        container.addView(Ui.sectionTitle(this, "Saved Expenses"))

        if (filteredExpenses.isEmpty()) {
            container.addView(Ui.muted(this, "No expenses found. Add your first expense."))
            return
        }

        filteredExpenses.forEach { expense ->
            container.addView(expenseCard(expense))
        }
    }

    private fun expenseCard(expense: Expense): LinearLayout {
        val card = Ui.card(this)
        card.addView(Ui.sectionTitle(this, expense.title))
        card.addView(Ui.body(this, MoneyUtils.format(expense.amount)))
        card.addView(Ui.muted(this, "${expense.category} • ${expense.date}"))
        if (expense.note.isNotBlank()) {
            card.addView(Ui.muted(this, "Note: ${expense.note}"))
        }
        card.addView(Ui.dangerButton(this, "Delete") {
            confirmDelete(expense)
        })
        return card
    }

    private fun confirmDelete(expense: Expense) {
        AlertDialog.Builder(this)
            .setTitle("Delete Expense")
            .setMessage("Delete ${expense.title}?")
            .setPositiveButton("Delete") { _, _ ->
                expenseStore.deleteExpense(expense.id)
                Ui.toast(this, "Expense deleted")
                render()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun shareReport() {
        val expenses = expenseStore.getExpenses()
        val total = expenses.sumOf { it.amount }
        val report = buildString {
            appendLine("Campus Expense Tracker Report")
            appendLine("Total Spent: ${MoneyUtils.format(total)}")
            appendLine()
            if (expenses.isEmpty()) {
                appendLine("No expenses added yet.")
            } else {
                expenses.forEach { expense ->
                    appendLine("- ${expense.title}: ${MoneyUtils.format(expense.amount)} (${expense.category}, ${expense.date})")
                }
            }
        }

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Campus Expense Report")
            putExtra(Intent.EXTRA_TEXT, report)
        }
        startActivity(Intent.createChooser(intent, "Share Report"))
    }

    companion object {
        private const val LIST_START_INDEX = 8
    }
}
