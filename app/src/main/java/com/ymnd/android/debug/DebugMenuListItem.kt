package com.ymnd.android.debug

import androidx.annotation.LayoutRes
import com.ymnd.android.R

/**
 * 開発者モードの各リストを定義する
 */
sealed class DebugMenuListItem {

    enum class DebugListType(@LayoutRes val layoutId: Int) {
        SIMPLE_LIST(R.layout.debug_menu_simple_list_item),
        BUTTON_LIST(R.layout.debug_menu_button_item),
    }

    abstract val category: DebugListType

    data class SimpleListItem(
        val title: String,
        val description: String,
        override val category: DebugListType = DebugListType.SIMPLE_LIST
    ) : DebugMenuListItem()

    data class ButtonListItem(
        val title: String,
        val description: String,
        val submit: String,
        val debugItemId: DebugItemID,
        override val category: DebugListType = DebugListType.BUTTON_LIST
    ) : DebugMenuListItem()
}

enum class DebugItemID {
    HOGE,
    FUGA,
}