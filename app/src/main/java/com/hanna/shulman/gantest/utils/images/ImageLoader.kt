package com.hanna.shulman.gantest.utils.images

import android.content.Context
import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, view: ImageView)
}