package com.example.taskwise_eventmaster.di

import com.example.taskwise_eventmaster.domain.repository.EventRepository
import com.example.taskwise_eventmaster.domain.repository.RoomEventRepository
import com.example.taskwise_eventmaster.domain.repository.RoomTaskRepository
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTaskRepositoryImpl(
        roomTaskRepository: RoomTaskRepository
    ): TaskRepository

    @Binds
    abstract fun bindEventRepositoryImpl(
        roomEventRepository: RoomEventRepository
    ): EventRepository
}