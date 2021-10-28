package com.example.yavintest.ui.component

import com.example.yavintest.ui.recyclerview.ItemActionBinding

class TicketViewAction(private val optionClick: () -> Unit) : ItemActionBinding() {

    fun optionClick() {
        debounceClick(optionClick)
    }
}