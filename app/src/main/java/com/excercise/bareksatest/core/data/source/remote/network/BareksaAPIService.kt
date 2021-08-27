package com.excercise.bareksatest.core.data.source.remote.network

import com.excercise.bareksatest.core.data.source.remote.response.ListChartResponse
import com.excercise.bareksatest.core.data.source.remote.response.ListDetailProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BareksaAPIService {
    @GET("takehometest/apps/compare/chart")
    suspend fun getChartMultipleProduct(
        @Query("productCodes") productCodesOne: String,
        @Query("productCodes") productCodesTwo: String,
        @Query("productCodes") productCodesThree: String?
    ): Response<ListChartResponse>

    @GET("takehometest/apps/compare/detail")
    suspend fun getDetailMultipleProduct(
        @Query("productCodes") productCodesOne: String,
        @Query("productCodes") productCodesTwo: String,
        @Query("productCodes") productCodesThree: String?
    ): Response<ListDetailProductResponse>
}