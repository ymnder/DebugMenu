package com.ymnd.android.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import com.ymnd.android.databinding.DebugMenuButtonItemBinding
import com.ymnd.android.databinding.DebugMenuSimpleListItemBinding
import com.ymnd.android.debug.DebugMenuListItem

class DebugMenuAdapter(
    context: Context,
    observableList: ObservableList<DebugMenuListItem> = ObservableArrayList(),
    private val buttonClickListener: (item: DebugMenuListItem) -> Unit
) : ObservableListRecyclerAdapter<DebugMenuListItem, BindingHolder<ViewDataBinding>>(
    context,
    observableList
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder<ViewDataBinding> {
        return BindingHolder(context, parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingHolder<ViewDataBinding>, position: Int) {
        val binding = holder.binding
        when (val item = getItem(position)) {
            is DebugMenuListItem.SimpleListItem -> (binding as DebugMenuSimpleListItemBinding).item =
                item
            is DebugMenuListItem.ButtonListItem -> {
                (binding as DebugMenuButtonItemBinding).item = item
                binding.button.setOnClickListener {
                    buttonClickListener(item)
                }
            }
        }.run {
            // whenでsealedの網羅性のチェックをしたい場合、値として評価しなければならない
            // 最後にまとめて実行する処理を追加したいので、値は使わないがrunを付加しreturnせずとも評価した
            binding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int = (getItem(position).category).layoutId
}