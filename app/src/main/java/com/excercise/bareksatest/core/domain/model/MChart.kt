package com.excercise.bareksatest.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MChart(
    val nameProduct: String,
    val listDataChartProduct: List<MChartDataProduct>?
) : Parcelable

@Parcelize
data class MChartDataProduct(
    val date: String,
    val growth: Double,
    val value: Double
) : Parcelable