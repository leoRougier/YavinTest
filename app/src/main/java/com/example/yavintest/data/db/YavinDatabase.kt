package com.example.yavintest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yavintest.data.dao.TicketDao
import com.example.yavintest.data.entity.Ticket
import com.example.yavintest.data.entity.Transaction

@Database(
    entities = [Ticket::class, Transaction::class], version = 1, exportSchema = false
)

abstract class YavinDatabase : RoomDatabase() {

    abstract fun ticketDao(): TicketDao

    companion object {
        @Volatile
        private var INSTANCE: YavinDatabase? = null

        fun getDatabase(context: Context): YavinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YavinDatabase::class.java,
                    "yavin_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

