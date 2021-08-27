package com.excercise.bareksatest.core.domain.usecase.product

import com.excercise.bareksatest.core.data.Resource
import com.excercise.bareksatest.core.domain.model.MChart
import com.excercise.bareksatest.core.domain.model.MDetailProduct
import kotlinx.coroutines.flow.Flow

interface ProductBareksaUseCase {
    fun getAllChartDataProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<Resource<List<MChart>>>

    fun getMultipleDetailProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<Resource<List<MDetailProduct>>>
}