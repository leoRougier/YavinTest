package com.example.yavintest.ui.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.yavintest.BR

open class ViewHolder<Model : BindableItem>(
    open val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Model) {
        if (binding.setVariable(BR.itemDataBinding, model)) {
            binding.executePendingBindings()
        }
    }
}