package com.example.taskwise_eventmaster.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.taskwise_eventmaster.model.Goal
import com.example.taskwise_eventmaster.model.categories.GoalCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Upsert
    suspend fun upsertCategory(category: GoalCategory)

    @Delete
    suspend fun deleteCategory(category: GoalCategory)

    @Query("SELECT * FROM Category ORDER BY name ASC")
    fun getCategoriesOrderedByName(): Flow<List<GoalCategory>>
}