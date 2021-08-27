package com.excercise.bareksatest.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chart")
data class ChartEntity(
    @PrimaryKey(autoGenerate = true)
    var pkChart: Long,
    var nameProduct: String,
    var date: String,
    var growth: Double,
    var value: Double
)