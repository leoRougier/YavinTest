package com.example.yavintest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yavintest.data.entity.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `Transaction` ORDER BY id DESC LIMIT 1")
    suspend fun getCurrentTransaction()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)
}