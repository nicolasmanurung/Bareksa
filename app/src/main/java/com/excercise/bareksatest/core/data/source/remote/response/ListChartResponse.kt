package com.excercise.bareksatest.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListChartResponse(
    val code: Int,
    val `data`: ChartName,
    val error: String,
    val message: String,
    val total_data: Int
) : Parcelable

@Parcelize
data class ChartName(
    val KI002MMCDANCAS00: KI002MMCDANCAS00,
    val NI002EQCDANSIE00: NI002EQCDANSIE00,
    val TP002EQCEQTCRS00: TP002EQCEQTCRS00
) : Parcelable

@Parcelize
data class KI002MMCDANCAS00(
    val `data`: List<DataChartOne>,
    val error: String
) : Parcelable

@Parcelize
data class NI002EQCDANSIE00(
    val `data`: List<DataChartTwo>,
    val error: String
) : Parcelable

@Parcelize
data class TP002EQCEQTCRS00(
    val `data`: List<DataChartThree>,
    val error: String
) : Parcelable

@Parcelize
data class DataChartOne(
    val date: String,
    val growth: Double,
    val value: Double
) : Parcelable

@Parcelize
data class DataChartTwo(
    val date: String,
    val growth: Double,
    val value: Double
) : Parcelable

@Parcelize
data class DataChartThree(
    val date: String,
    val growth: Double,
    val value: Double
) : Parcelable