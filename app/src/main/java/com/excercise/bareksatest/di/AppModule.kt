package com.excercise.bareksatest.di

import com.excercise.bareksatest.core.domain.usecase.product.ProductBareksaInteractor
import com.excercise.bareksatest.core.domain.usecase.product.ProductBareksaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideProductBareksaUseCase(productBareksaInteractor: ProductBareksaInteractor): ProductBareksaUseCase
}