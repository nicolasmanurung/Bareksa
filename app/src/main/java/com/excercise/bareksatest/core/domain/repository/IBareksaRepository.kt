package com.excercise.bareksatest.core.domain.repository

import com.excercise.bareksatest.core.data.Resource
import com.excercise.bareksatest.core.domain.model.MChart
import com.excercise.bareksatest.core.domain.model.MDetailProduct
import kotlinx.coroutines.flow.Flow

interface IBareksaRepository {

    fun getChartProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<Resource<List<MChart>>>

    fun getDetailProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ): Flow<Resource<List<MDetailProduct>>>
}