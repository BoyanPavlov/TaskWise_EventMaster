package com.example.taskwise_eventmaster.di

import android.content.Context
import androidx.room.Room
import com.example.taskwise_eventmaster.data.daos.TaskDao
import com.example.taskwise_eventmaster.data.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, "TasksDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(database: Database): TaskDao = database.taskDao

}