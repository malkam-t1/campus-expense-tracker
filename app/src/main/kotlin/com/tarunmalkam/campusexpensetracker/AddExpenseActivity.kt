package com.tarunmalkam.campusexpensetracker

import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.Spinner

class AddExpenseActivity : Activity() {
    private lateinit var expenseStore: ExpenseStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        expenseStore = ExpenseStore(this)
        render()
    }

    private fun render() {
        val container = Ui.screen(this)

        container.addView(Ui.title(this, "Add Expense"))
        container.addView(Ui.subtitle(this, "Save a daily campus expense with amount, category, date, and note."))

        val titleInput = Ui.input(this, "Expense title, e.g. Lunch")
        val amountInput = Ui.input(this, "Amount in ₹")
        amountInput.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        val categorySpinner = Spinner(this)
        categorySpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Category.all
        )

        val dateInput = Ui.input(this, "Date: yyyy-MM-dd")
        dateInput.setText(DateUtils.today())

        val noteInput = Ui.input(this, "Note, optional")
        noteInput.minLines = 2

        container.addView(Ui.sectionTitle(this, "Expense Details"))
        container.addView(titleInput)
        container.addView(amountInput)
        container.addView(Ui.muted(this, "Category"))
        container.addView(categorySpinner)
        container.addView(dateInput)
        container.addView(noteInput)

        container.addView(Ui.button(this, "Save Expense") {
            val title = titleInput.text.toString().trim()
            val amount = amountInput.text.toString().trim().toDoubleOrNull()
            val category = categorySpinner.selectedItem.toString()
            val date = dateInput.text.toString().trim()
            val note = noteInput.text.toString().trim()

            when {
                title.isBlank() -> Ui.toast(this, "Enter expense title")
                amount == null || amount <= 0.0 -> Ui.toast(this, "Enter a valid amount")
                !date.matches(Regex("\\d{4}-\\d{2}-\\d{2}")) -> Ui.toast(this, "Use date format yyyy-MM-dd")
                else -> {
                    expenseStore.addExpense(
                        Expense(
                            id = System.currentTimeMillis(),
                            title = title,
                            amount = amount,
                            category = category,
                            date = date,
                            note = note
                        )
                    )
                    Ui.toast(this, "Expense saved")
                    finish()
                }
            }
        })

        container.addView(Ui.outlineButton(this, "Cancel") { finish() })
    }
}
