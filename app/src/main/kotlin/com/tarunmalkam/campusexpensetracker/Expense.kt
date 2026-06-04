package com.tarunmalkam.campusexpensetracker

data class Expense(
    val id: Long,
    val title: String,
    val amount: Double,
    val category: String,
    val date: String,
    val note: String
)
