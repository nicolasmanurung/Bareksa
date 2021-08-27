package com.excercise.bareksatest.utils

enum class FilterChart(val countByDays: Int) {
    // Note Sabtu - Minggu libur = -2 each week
    ONEWEEK(7 - 2),
    ONEMONTH(31 - 8),
    ONEYEAR(365 - 104),
    THREEYEAR(1095 - 321),
    FIFTHYEAR(1825 - 1605),
    TENTHYEAR(3650 - 1040)
}