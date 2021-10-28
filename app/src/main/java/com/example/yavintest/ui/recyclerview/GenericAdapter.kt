package com.example.yavintest.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


open class GenericAdapter : RecyclerView.Adapter<ViewHolder<BindableItem>>() {
    private var itemList: MutableList<BindableItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<BindableItem> {
        val binding: ViewDataBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), viewType,
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder<BindableItem>, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(itemList[position])
    }

    fun getLayoutId(model: BindableItem): Int {
        return model.layoutId
    }

    fun setItemList(list: List<BindableItem>) {
        val diffCallback = DiffCallback<BindableItem>()
        diffCallback.setLists(this.itemList, list)
        val result = DiffUtil.calculateDiff(diffCallback)
        this.itemList.clear()
        this.itemList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    fun getItemList(): List<BindableItem> {
        return itemList
    }
}

class DiffCallback<T : Comparable<T>> : DiffUtil.Callback() {
    private var oldList: List<T> = emptyList()
    private var newList: List<T> = emptyList()

    fun setLists(oldList: List<T>, newList: List<T>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.getOrNull(oldItemPosition) ?: return false
        val newItemItem = newList.getOrNull(newItemPosition) ?: return false
        return oldItem.compareTo(newItemItem) == 0
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}
