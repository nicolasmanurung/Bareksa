package com.excercise.bareksatest.core.data.source.remote

import com.excercise.bareksatest.core.data.source.remote.network.BareksaAPIService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: BareksaAPIService) {
}