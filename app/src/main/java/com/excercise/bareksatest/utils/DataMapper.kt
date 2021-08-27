package com.excercise.bareksatest.utils

import com.excercise.bareksatest.core.data.source.local.entity.ChartEntity
import com.excercise.bareksatest.core.data.source.local.entity.DetailProductEntity
import com.excercise.bareksatest.core.data.source.remote.response.ChartName
import com.excercise.bareksatest.core.data.source.remote.response.Product
import com.excercise.bareksatest.core.domain.model.MChart
import com.excercise.bareksatest.core.domain.model.MChartDataProduct
import com.excercise.bareksatest.core.domain.model.MDetailProduct

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
        val productOneChartData = ArrayList<MChartDataProduct>()
        val productTwoChartData = ArrayList<MChartDataProduct>()
        val productThreeChartData = ArrayList<MChartDataProduct>()

        val listMChart = ArrayList<MChart>()
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


    fun mapProductDetailResponseToEntities(input: List<Product>): List<DetailProductEntity> {
        return input.map { product ->
            DetailProductEntity(
                codeName = product.code,
                name = product.name,
                category = product.details.category,
                category_id = product.details.category_id,
                currency = product.details.currency,
                custody = product.details.custody,
                im_avatar = product.details.im_avatar,
                im_name = product.details.im_name,
                inception_date = product.details.inception_date,
                min_balance = product.details.min_balance,
                min_redemption = product.details.min_redemption,
                min_subscription = product.details.min_subscription,
                nav = product.details.nav,
                return_cur_year = product.details.return_cur_year,
                return_five_year = product.details.return_five_year,
                return_four_year = product.details.return_four_year,
                return_inception_growth = product.details.return_inception_growth,
                return_one_day = product.details.return_one_day,
                return_one_month = product.details.return_one_month,
                return_one_week = product.details.return_one_week,
                return_one_year = product.details.return_one_year,
                return_six_month = product.details.return_six_month,
                return_three_month = product.details.return_three_month,
                return_three_year = product.details.return_three_year,
                return_two_year = product.details.return_two_year,
                total_unit = product.details.total_unit,
                type = product.details.type,
                type_id = product.details.type_id
            )
        }
    }

    fun mapProductDetailEntityToDomain(input: List<DetailProductEntity>): List<MDetailProduct> {
        return input.map { product ->
            MDetailProduct(
                codeName = product.codeName,
                name = product.name,
                category = product.category,
                category_id = product.category_id,
                currency = product.currency,
                custody = product.custody,
                im_avatar = product.im_avatar,
                im_name = product.im_name,
                inception_date = product.inception_date,
                min_balance = product.min_balance,
                min_redemption = product.min_redemption,
                min_subscription = product.min_subscription,
                nav = product.nav,
                return_cur_year = product.return_cur_year,
                return_five_year = product.return_five_year,
                return_four_year = product.return_four_year,
                return_inception_growth = product.return_inception_growth,
                return_one_day = product.return_one_day,
                return_one_month = product.return_one_month,
                return_one_week = product.return_one_week,
                return_one_year = product.return_one_year,
                return_six_month = product.return_six_month,
                return_three_month = product.return_three_month,
                return_three_year = product.return_three_year,
                return_two_year = product.return_two_year,
                total_unit = product.total_unit,
                type = product.type,
                type_id = product.type_id
            )
        }
    }
}