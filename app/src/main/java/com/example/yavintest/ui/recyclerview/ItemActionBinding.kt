package com.example.yavintest.ui.recyclerview

open class ItemActionBinding {

    private val debounceDelay: Int = 500
    private var lastClickTime: Long = 0

    fun debounceClick(listener: (() -> Unit)) {
        val now = org.joda.time.Instant.now().millis

        if (now - lastClickTime > debounceDelay) {
            lastClickTime = now
            listener()
        }
    }
}