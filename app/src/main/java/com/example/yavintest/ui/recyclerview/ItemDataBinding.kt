package com.example.yavintest.ui.recyclerview

import androidx.databinding.BaseObservable

open class ItemDataBinding: BaseObservable(), Comparable<ItemDataBinding> {

    override fun compareTo(other: ItemDataBinding): Int {
        return 1
    }
}