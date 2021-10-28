package com.example.yavintest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transaction")
data class Transaction(@PrimaryKey(autoGenerate = true) val id: Int, var tickets: Int)
