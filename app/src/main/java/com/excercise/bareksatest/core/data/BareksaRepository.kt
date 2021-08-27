package com.excercise.bareksatest.core.data

import com.excercise.bareksatest.core.data.source.local.LocalDataSource
import com.excercise.bareksatest.core.data.source.remote.RemoteDataSource
import com.excercise.bareksatest.core.data.source.remote.network.ApiResponse
import com.excercise.bareksatest.core.data.source.remote.response.ChartName
import com.excercise.bareksatest.core.data.source.remote.response.Product
import com.excercise.bareksatest.core.domain.model.MChart
import com.excercise.bareksatest.core.domain.model.MDetailProduct
import com.excercise.bareksatest.core.domain.repository.IBareksaRepository
import com.excercise.bareksatest.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BareksaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IBareksaRepository {
    override fun getChartProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<Resource<List<MChart>>> = object : NetworkBoundResource<List<MChart>, ChartName>() {
        override fun loadFromDB(): Flow<List<MChart>> {
            return localDataSource.getMultipleChartData(productOne, productTwo, productThree).map {
                DataMapper.mapChartEntityToDomain(it)
            }
        }

        override fun shouldFetch(data: List<MChart>?): Boolean {
            return true
        }

        override suspend fun createCall(): Flow<ApiResponse<ChartName>> {
            return remoteDataSource.getAllChartData(productOne, productTwo, productThree)
        }

        override suspend fun saveCallResult(data: ChartName) {
            val chartList = DataMapper.mapChartDataResponseToEntities(data)
            localDataSource.insertListChart(chartList)
        }
    }.asFlow()

    override fun getDetailProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<Resource<List<MDetailProduct>>> =
        object : NetworkBoundResource<List<MDetailProduct>, List<Product>>() {
            override fun loadFromDB(): Flow<List<MDetailProduct>> {
                return localDataSource.getMultipleDetailProduct(
                    productOne,
                    productTwo,
                    productThree
                ).map {
                    DataMapper.mapProductDetailEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MDetailProduct>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<Product>>> {
                return remoteDataSource.getMultipleDetailProduct(
                    productOne,
                    productTwo,
                    productThree
                )
            }

            override suspend fun saveCallResult(data: List<Product>) {
                val detailProductList = DataMapper.mapProductDetailResponseToEntities(data)
                localDataSource.insertListDetailProduct(detailProductList)
            }
        }.asFlow()
}