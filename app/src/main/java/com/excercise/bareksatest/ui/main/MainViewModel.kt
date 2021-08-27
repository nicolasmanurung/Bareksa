package com.excercise.bareksatest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.excercise.bareksatest.core.domain.usecase.product.ProductBareksaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productBareksaUseCase: ProductBareksaUseCase
) : ViewModel() {
    fun getMultipleChartProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ) = productBareksaUseCase.getAllChartDataProducts(productOne, productTwo, productThree)
        .asLiveData()

    fun getMultipleDetailProducts(
        productOne: String,
        productTwo: String,
        productThree: String?
    ) = productBareksaUseCase.getMultipleDetailProducts(productOne, productTwo, productThree)
        .asLiveData()
}