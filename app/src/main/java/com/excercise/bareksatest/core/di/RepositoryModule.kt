package com.excercise.bareksatest.core.di

import com.excercise.bareksatest.core.data.BareksaRepository
import com.excercise.bareksatest.core.domain.repository.IBareksaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(bareksaRepository: BareksaRepository): IBareksaRepository
}