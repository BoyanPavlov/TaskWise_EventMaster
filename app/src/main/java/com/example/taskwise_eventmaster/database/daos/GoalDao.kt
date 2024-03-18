package com.example.Goalwise_eventmaster.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.taskwise_eventmaster.model.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Upsert
    suspend fun upsertGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Query("SELECT * FROM Goal ORDER BY title ASC")
    fun getGoalsOrderedByTitle(): Flow<List<Goal>>

    @Query("SELECT * FROM Goal ORDER BY category ASC")
    fun getGoalsOrderedByGoalName(): Flow<List<Goal>>

    @Query("SELECT * FROM Goal ORDER BY estimationTime ASC")
    fun getGoalsOrderedByDate(): Flow<List<Goal>>
}