package com.ymnd.android.adapter

import android.content.Context
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView

abstract class ArrayRecyclerAdapter<T, VH : RecyclerView.ViewHolder> constructor(
    val context: Context,
    private val list: MutableList<T>
) :
    RecyclerView.Adapter<VH>(), Iterable<T> {

    override fun getItemCount(): Int {
        return list.size
    }

    @UiThread
    fun reset(items: Collection<T>) {
        clear()
        addAll(items)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return list[position]
    }

    fun addItem(item: T) {
        list.add(item)
    }

    private fun addAll(items: Collection<T>) {
        list.addAll(items)
    }

    fun addAll(position: Int, items: Collection<T>) {
        list.addAll(position, items)
    }

    @UiThread
    fun addAllWithNotification(items: Collection<T>) {
        val position = itemCount
        addAll(items)
        notifyItemInserted(position)
    }

    private fun clear() {
        list.clear()
    }

    override fun iterator(): Iterator<T> {
        return list.iterator()
    }

}