package com.example.yavintest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yavintest.data.entity.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {

    @Query("SELECT * FROM Tickets")
    fun getTickets(): Flow<List<Ticket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticket: Ticket)

    @Query("DELETE FROM Tickets")
    suspend fun deleteAll()

    @Query("UPDATE Tickets SET price=:price WHERE id = :id")
    suspend fun update(price: Double?, id: Int)

}