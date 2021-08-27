package com.excercise.bareksatest.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.round

object CustomFunctions {
    fun parseDateTimeToMonthDay(dateTime: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("MMM dd", Locale.getDefault())
        return formatter.format(parser.parse(dateTime))
    }

    fun parseDateTimeToDateTimeRead(dateTime: String): String{
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return formatter.format(parser.parse(dateTime))
    }

    fun parseValueIntoMoneyExtension(dataMoney: Double): String {
        var numberString = ""
        numberString = when {
            abs(dataMoney / 1000000000000) > 1 -> {
                round(dataMoney / 1000000000000).toString() + " Triliun"
            }
            abs(dataMoney / 1000000000) > 1 -> {
                round(dataMoney / 1000000000).toString() + " Miliar"
            }
            abs(dataMoney / 1000000) > 1 -> {
                round(dataMoney / 1000000).toString() + " Juta"
            }
            abs(dataMoney / 1000) > 1 -> {
                round(dataMoney / 1000).toString() + " Ribu"
            }
            else -> {
                dataMoney.toString()
            }
        }

        return numberString
    }

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
}