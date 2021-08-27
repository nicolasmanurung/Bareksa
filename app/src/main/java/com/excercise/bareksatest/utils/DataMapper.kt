package com.excercise.bareksatest.utils

import com.excercise.bareksatest.core.data.source.local.entity.ChartEntity
import com.excercise.bareksatest.core.data.source.remote.response.ChartName
import com.excercise.bareksatest.core.domain.model.MChart
import com.excercise.bareksatest.core.domain.model.MChartDataProduct

object DataMapper {

    fun mapChartDataResponseToEntities(input: ChartName): List<ChartEntity> {
        val chartList = ArrayList<ChartEntity>()
        input.KI002MMCDANCAS00.data.map { dataItem ->
            val newChart = ChartEntity(
                pkChart = 0,
                nameProduct = "KI002MMCDANCAS00",
                date = dataItem.date,
                growth = dataItem.growth,
                value = dataItem.value
            )
            chartList.add(newChart)
        }

        input.NI002EQCDANSIE00.data.map { dataItem ->
            val newChart = ChartEntity(
                pkChart = 0,
                nameProduct = "NI002EQCDANSIE00",
                date = dataItem.date,
                growth = dataItem.growth,
                value = dataItem.value
            )
            chartList.add(newChart)
        }

        input.TP002EQCEQTCRS00.data.map { dataItem ->
            val newChart = ChartEntity(
                pkChart = 0,
                nameProduct = "TP002EQCEQTCRS00",
                date = dataItem.date,
                growth = dataItem.growth,
                value = dataItem.value
            )
            chartList.add(newChart)
        }
        return chartList
    }

    fun mapChartEntityToDomain(input: List<ChartEntity>): List<MChart> {
        var productOneChartData = ArrayList<MChartDataProduct>()
        var productTwoChartData = ArrayList<MChartDataProduct>()
        var productThreeChartData = ArrayList<MChartDataProduct>()

        var listMChart = ArrayList<MChart>()
        input.forEach {
            when (it.nameProduct) {
                "KI002MMCDANCAS00" -> {
                    productOneChartData.add(
                        MChartDataProduct(
                            date = it.date,
                            growth = it.growth,
                            value = it.value
                        )
                    )
                }

                "NI002EQCDANSIE00" -> {
                    productTwoChartData.add(
                        MChartDataProduct(
                            date = it.date,
                            growth = it.growth,
                            value = it.value
                        )
                    )
                }

                "TP002EQCEQTCRS00" -> {
                    productThreeChartData.add(
                        MChartDataProduct(
                            date = it.date,
                            growth = it.growth,
                            value = it.value
                        )
                    )
                }
            }
        }

        listMChart.add(MChart("KI002MMCDANCAS00", productOneChartData))
        listMChart.add(MChart("NI002EQCDANSIE00", productTwoChartData))
        listMChart.add(MChart("TP002EQCEQTCRS00", productThreeChartData))

        return listMChart
    }
}