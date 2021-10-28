package com.example.yavintest.ui.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * This class override the default RecyclerView to apply custom theme and our generic adapter.
 */
class BaseRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun BaseRecyclerView.bindItems(items: List<BindableItem>?) {
            items?.let {
                genericAdapter.setItemList(items)
            }
        }
    }

    internal val genericAdapter = GenericAdapter()

    init {
        super.setAdapter(genericAdapter)
        if (layoutManager == null) {
            layoutManager = LinearLayoutManager(context)
        }
    }

    /**
     * Parse attributes used in xml to style the view.
     */
}

