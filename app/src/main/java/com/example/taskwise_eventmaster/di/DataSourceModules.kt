package com.example.taskwise_eventmaster.di

import com.example.taskwise_eventmaster.data.network.datasources.OkHttpEventDataSource
import com.example.taskwise_eventmaster.data.network.datasources.RemoteEventDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModules {

    @Binds
    @Singleton
    abstract fun bindEventDataSourceImpl(
        dataSource: OkHttpEventDataSource
    ): RemoteEventDataSource
}