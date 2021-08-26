package com.excercise.bareksatest.core.data.source.local

import com.excercise.bareksatest.core.data.source.local.room.BareksaDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val bareksaDao: BareksaDao) {
}