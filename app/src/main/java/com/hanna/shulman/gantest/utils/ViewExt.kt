package com.hanna.shulman.gantest.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layout: Int, attach: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attach)
}