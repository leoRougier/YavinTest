package com.example.yavintest.ui.recyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BaseObservable

abstract class BindableItem(
    @LayoutRes open val layoutId: Int
) : BaseObservable(), Comparable<BindableItem> {

    override fun compareTo(other: BindableItem): Int {
        return other.data.compareTo(data)
    }

    open fun init() {}

    abstract val data : ItemDataBinding
    abstract val action : ItemActionBinding
}