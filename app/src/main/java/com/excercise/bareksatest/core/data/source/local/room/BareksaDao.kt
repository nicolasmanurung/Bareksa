package com.excercise.bareksatest.core.data.source.local.room

import androidx.room.*
import com.excercise.bareksatest.core.data.source.local.entity.ChartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BareksaDao {
    // SELECT
    @Query("SELECT * FROM chart WHERE nameProduct IN (:productsCodeName)")
    fun getMultipleChart(
        productsCodeName: ArrayList<String>
    ): Flow<List<ChartEntity>>


    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListChart(chartList: List<ChartEntity>)

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