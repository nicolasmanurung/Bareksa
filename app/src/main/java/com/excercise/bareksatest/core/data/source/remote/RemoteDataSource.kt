package com.excercise.bareksatest.core.data.source.remote

import android.util.Log
import com.excercise.bareksatest.core.data.source.remote.network.ApiResponse
import com.excercise.bareksatest.core.data.source.remote.network.BareksaAPIService
import com.excercise.bareksatest.core.data.source.remote.response.ChartName
import com.excercise.bareksatest.core.data.source.remote.response.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: BareksaAPIService) {

    suspend fun getAllChartData(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<ApiResponse<ChartName>> {
        return flow {
            try {
                val response =
                    apiService.getChartMultipleProduct(productOne, productTwo, productThree)
                if (response.isSuccessful) {
                    val dataResponse = response.body()?.data
                    if (dataResponse != null) {
                        emit(ApiResponse.Success(dataResponse))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                } else {
                    emit(ApiResponse.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMultipleDetailProduct(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<ApiResponse<List<Product>>> {
        return flow {
            try {
                val response =
                    apiService.getDetailMultipleProduct(productOne, productTwo, productThree)
                if (response.isSuccessful) {
                    val dataResponse = response.body()?.data
                    if (dataResponse?.isNotEmpty() == true) {
                        emit(ApiResponse.Success(dataResponse))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                } else {
                    emit(ApiResponse.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}