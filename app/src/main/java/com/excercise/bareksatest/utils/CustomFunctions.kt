package com.excercise.bareksatest.utils

import java.text.SimpleDateFormat
import java.util.*

object CustomFunctions {
    fun parseDateTimeToMonthDay(dateTime: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("MMM dd", Locale.getDefault())
        return formatter.format(parser.parse(dateTime))
    }
}