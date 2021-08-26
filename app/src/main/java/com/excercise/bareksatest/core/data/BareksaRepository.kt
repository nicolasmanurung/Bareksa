package com.excercise.bareksatest.core.data

import com.excercise.bareksatest.core.data.source.local.LocalDataSource
import com.excercise.bareksatest.core.data.source.remote.RemoteDataSource
import com.excercise.bareksatest.core.domain.repository.IBareksaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BareksaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IBareksaRepository {
}