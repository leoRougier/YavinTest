package com.example.yavintest.ui.component

import androidx.databinding.Bindable
import com.example.yavintest.BR
import com.example.yavintest.data.entity.Ticket
import com.example.yavintest.ui.recyclerview.ItemDataBinding


class TicketViewDatabinding(
    ticket: Ticket
) : ItemDataBinding() {

    @get:Bindable
    var title: String? = ticket.type.name
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var price: Double = ticket.price
        set(value) {
            field = value
            notifyPropertyChanged(BR.price)
        }

    @get:Bindable
    var total: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.total)
        }
    private val unitPrice = ticket.price

    fun increment() {
        total++
        updatePrice()
    }

    fun decrement() {
        total--
        if (total > 0) {
            updatePrice()
        }
    }

    private fun updatePrice() {
        price = unitPrice * total
    }
}