package com.ymnd.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingHolder<T : ViewDataBinding> constructor(
    context: Context,
    parent: ViewGroup, @LayoutRes layoutResId: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutResId, parent, false)) {

    val binding: T? = DataBindingUtil.bind(itemView)
}
