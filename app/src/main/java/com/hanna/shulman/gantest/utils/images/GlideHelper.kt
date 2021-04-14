package com.hanna.shulman.gantest.utils.images

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hanna.shulman.gantest.OpenForTesting
import com.hanna.shulman.gantest.R

@OpenForTesting
class GlideHelper : ImageLoader {
    override fun loadImage(url: String, view: ImageView) {
        Glide.with(view).load(url).placeholder(R.drawable.profile_placeholder).into(view)
    }
}