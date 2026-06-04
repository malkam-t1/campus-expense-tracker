package com.tarunmalkam.campusexpensetracker

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private val dayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val monthFormat = SimpleDateFormat("yyyy-MM", Locale.getDefault())

    fun today(): String = dayFormat.format(Date())

    fun currentMonth(): String = monthFormat.format(Date())
}
