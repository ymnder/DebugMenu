package com.ymnd.android.adapter

import android.content.Context
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

abstract class ObservableListRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(
    context: Context,
    observableList: ObservableList<T>
) : ArrayRecyclerAdapter<T, VH>(context, observableList) {

    init {
        observableList.addOnListChangedCallback(object :
            ObservableList.OnListChangedCallback<ObservableList<T>>() {
            override fun onChanged(sender: ObservableList<T>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableList<T>,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(
                sender: ObservableList<T>,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeMoved(
                sender: ObservableList<T>,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onItemRangeRemoved(
                sender: ObservableList<T>,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeRemoved(positionStart, itemCount)
            }
        })
    }
}