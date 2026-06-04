package com.tarunmalkam.campusexpensetracker

import java.text.NumberFormat
import java.util.Locale

object MoneyUtils {
    fun format(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        return formatter.format(amount)
    }
}
