package com.excercise.bareksatest.core.domain.usecase.product

import com.excercise.bareksatest.core.data.Resource
import com.excercise.bareksatest.core.domain.model.MChart
import kotlinx.coroutines.flow.Flow

interface ProductBareksaUseCase {
    fun getAllChartDataProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<Resource<List<MChart>>>
}