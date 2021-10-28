package com.example.yavintest.ui.component

import com.example.yavintest.R
import com.example.yavintest.ui.recyclerview.BindableItem
import com.example.yavintest.ui.recyclerview.ItemActionBinding

class TicketViewItem(override val data: TicketViewDatabinding, override val action: TicketViewAction) :
    BindableItem(R.layout.tickets_view) {

    override fun compareTo(other: BindableItem): Int {
        return 1
    }
}