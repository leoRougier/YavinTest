package com.example.yavintest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tickets")
data class Ticket (@PrimaryKey(autoGenerate = true) val id: Int, val price: Double,val type: TicketType)

