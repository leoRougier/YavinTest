package com.example.yavintest.ui.main

import androidx.databinding.Bindable
import com.example.yavintest.BR
import com.example.yavintest.ui.component.TicketViewItem
import com.example.yavintest.ui.recyclerview.ItemDataBinding

class MainFragmentDatabinding(item: List<TicketViewItem>) : ItemDataBinding() {
    @Bindable
    var items: List<TicketViewItem> = item
        set(value) {
            field = value
            notifyPropertyChanged(BR.items)
        }

}