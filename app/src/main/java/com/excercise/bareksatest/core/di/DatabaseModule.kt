package com.excercise.bareksatest.core.di

import android.content.Context
import androidx.room.Room
import com.excercise.bareksatest.core.data.source.local.room.BareksaDao
import com.excercise.bareksatest.core.data.source.local.room.BareksaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BareksaDatabase =
        Room.databaseBuilder(
            context,
            BareksaDatabase::class.java, "bareksa.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideBareksaDao(database: BareksaDatabase): BareksaDao = database.bareksaDao()
}