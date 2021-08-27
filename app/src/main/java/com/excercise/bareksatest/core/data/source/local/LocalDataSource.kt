package com.excercise.bareksatest.core.data.source.local

import com.excercise.bareksatest.core.data.source.local.entity.ChartEntity
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


    // INSERT DATA ENTITY
    suspend fun insertListChart(chartList: List<ChartEntity>) =
        bareksaDao.updateChartDataFromServer(chartList)
}