package com.excercise.bareksatest.core.data.source.local.room

import androidx.room.*
import com.excercise.bareksatest.core.data.source.local.entity.ChartEntity
import com.excercise.bareksatest.core.data.source.local.entity.DetailProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BareksaDao {
    // SELECT
    @Query("SELECT * FROM chart WHERE nameProduct IN (:productsCodeName)")
    fun getMultipleChart(
        productsCodeName: ArrayList<String>
    ): Flow<List<ChartEntity>>

    @Query("SELECT * FROM detailProduct WHERE codeName IN (:productsCodeName)")
    fun getMultipleDataProduct(
        productsCodeName: ArrayList<String>
    ): Flow<List<DetailProductEntity>>

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListChart(chartList: List<ChartEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListDetailProduct(productList: List<DetailProductEntity>)


    // DELETE
    @Query("DELETE FROM chart")
    suspend fun deleteAllChartData()

    // Transaction

    @Transaction
    suspend fun updateChartDataFromServer(chartList: List<ChartEntity>) {
        deleteAllChartData()
        insertListChart(chartList)
    }
}