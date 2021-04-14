package com.hanna.shulman.gantest.presentation.ui.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hanna.shulman.gantest.R
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.utils.BindableViewHolder
import com.hanna.shulman.gantest.utils.images.GlideHelper
import com.hanna.shulman.gantest.utils.images.ImageLoader

class ShowCharacterSummaryViewHolder(
    itemView: View,
    val onItemClick: (character: ShowCharacterSummary) -> Unit
) : BindableViewHolder<ShowCharacterSummary>(itemView) {

    var glideHelper: ImageLoader = GlideHelper()
    private val imageView: ImageView = itemView.findViewById(R.id.character_image)
    val characterName: TextView = itemView.findViewById(R.id.character_name)

    override fun bind(data: ShowCharacterSummary) {
        glideHelper.loadImage(data.img, imageView)
        characterName.text = data.name
        itemView.clipToOutline = true
        itemView.setOnClickListener {
            onItemClick(data)
        }
    }
}