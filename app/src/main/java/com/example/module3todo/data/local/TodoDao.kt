package com.example.module3todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY id ASC")
    fun observeTodos(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE id = :id LIMIT 1")
    suspend fun getTodoById(id: Int): TodoEntity?

    @Query("SELECT COUNT(*) FROM todos")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<TodoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TodoEntity)

    @Update
    suspend fun update(item: TodoEntity)

    @Delete
    suspend fun delete(item: TodoEntity)
}
