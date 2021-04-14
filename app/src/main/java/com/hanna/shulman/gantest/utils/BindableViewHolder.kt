package com.hanna.shulman.gantest.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BindableViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}