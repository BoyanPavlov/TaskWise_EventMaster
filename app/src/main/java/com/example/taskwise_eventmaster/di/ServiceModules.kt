package com.example.taskwise_eventmaster.di

import com.example.taskwise_eventmaster.domain.repository.EventRepository
import com.example.taskwise_eventmaster.domain.repository.OkHttpEventRepository
import com.example.taskwise_eventmaster.domain.repository.RoomTaskRepository
import com.example.taskwise_eventmaster.domain.repository.TaskRepository
import com.example.taskwise_eventmaster.domain.service.authorization.AuthService
import com.example.taskwise_eventmaster.domain.service.authorization.GoogleAuthService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModules {
    @Binds
    abstract fun bindAuthImplementation(
        googleAuthService: GoogleAuthService    //low lvl component
    ): AuthService                              //high lvl component

    @Binds
    abstract fun bindTaskRepositoryImpl(
        roomTaskRepository: RoomTaskRepository
    ): TaskRepository

    @Binds
    abstract fun bindEventRepositoryImpl(
        eventOkHttpRepository: OkHttpEventRepository
    ): EventRepository
}