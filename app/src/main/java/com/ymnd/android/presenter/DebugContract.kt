package com.ymnd.android.presenter

import com.ymnd.android.debug.DebugMenuCategory
import com.ymnd.android.debug.DebugMenuListItem

interface DebugContract {
    interface Presenter {
        fun setUp(view: View)
        fun setArguments(category: DebugMenuCategory)
        suspend fun onActivityCreated()
        suspend fun onRefresh()
    }

    interface View {
        fun updateDebugList(debugList: List<DebugMenuListItem>)
        fun showLoading()
        fun hideLoading()
    }
}