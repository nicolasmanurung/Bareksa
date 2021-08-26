package com.excercise.bareksatest.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [

    ],
    version = 1,
    exportSchema = false
)
abstract class BareksaDatabase : RoomDatabase() {
    abstract fun bareksaDao(): BareksaDao
}