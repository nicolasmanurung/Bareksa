package com.excercise.bareksatest.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.excercise.bareksatest.core.data.source.local.entity.ChartEntity
import com.excercise.bareksatest.core.data.source.local.entity.DetailProductEntity

@Database(
    entities = [
        ChartEntity::class,
        DetailProductEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BareksaDatabase : RoomDatabase() {
    abstract fun bareksaDao(): BareksaDao
}