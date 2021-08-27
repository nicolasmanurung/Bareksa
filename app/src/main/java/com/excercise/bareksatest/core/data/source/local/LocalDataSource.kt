package com.excercise.bareksatest.core.data.source.local

import com.excercise.bareksatest.core.data.source.local.entity.ChartEntity
import com.excercise.bareksatest.core.data.source.local.entity.DetailProductEntity
import com.excercise.bareksatest.core.data.source.local.room.BareksaDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val bareksaDao: BareksaDao) {

    // GET DATA ENTITY
    fun getMultipleChartData(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<List<ChartEntity>> {
        val listProductId = ArrayList<String>()
        listProductId.add(productOne)
        listProductId.add(productTwo)
        productThree?.let { listProductId.add(it) }
        return bareksaDao.getMultipleChart(listProductId)
    }

    fun getMultipleDetailProduct(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<List<DetailProductEntity>> {
        val listProductCode = ArrayList<String>()
        listProductCode.add(productOne)
        listProductCode.add(productTwo)
        productThree?.let { listProductCode.add(it) }
        return bareksaDao.getMultipleDataProduct(listProductCode)
    }


    // INSERT DATA ENTITY
    suspend fun insertListChart(chartList: List<ChartEntity>) =
        bareksaDao.updateChartDataFromServer(chartList)

    suspend fun insertListDetailProduct(listProductDetail: List<DetailProductEntity>) =
        bareksaDao.insertListDetailProduct(listProductDetail)
}