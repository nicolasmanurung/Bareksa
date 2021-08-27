package com.excercise.bareksatest.core.domain.usecase.product

import com.excercise.bareksatest.core.domain.repository.IBareksaRepository
import javax.inject.Inject

class ProductBareksaInteractor @Inject constructor(private val bareksaIRepository: IBareksaRepository) :
    ProductBareksaUseCase {
    override fun getAllChartDataProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ) = bareksaIRepository.getChartProducts(productOne, productTwo, productThree)

    override fun getMultipleDetailProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ) = bareksaIRepository.getDetailProducts(productOne, productTwo, productThree)
}