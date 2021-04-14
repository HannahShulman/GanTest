package com.hanna.shulman.gantest.presentation.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hanna.shulman.gantest.R
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.utils.BindableViewHolder
import com.hanna.shulman.gantest.utils.inflate

class ShowCharacterSummaryAdapter(private val onItemClick: (character: ShowCharacterSummary) -> Unit) :
    ListAdapter<ShowCharacterSummary, BindableViewHolder<ShowCharacterSummary>>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ShowCharacterSummary>() {
            override fun areItemsTheSame(
                oldItem: ShowCharacterSummary,
                newItem: ShowCharacterSummary
            ): Boolean {
                return oldItem.charId == newItem.charId
            }

            override fun areContentsTheSame(
                oldItem: ShowCharacterSummary,
                newItem: ShowCharacterSummary
            ): Boolean {
                return oldItem.img == newItem.img && oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<ShowCharacterSummary> {
        return ShowCharacterSummaryViewHolder(
            parent.inflate(R.layout.single_show_character_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: BindableViewHolder<ShowCharacterSummary>, position: Int) {
        holder.bind(currentList[position])
    }
}