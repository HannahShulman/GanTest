package com.hanna.shulman.gantest.domain.model.entities

import androidx.room.TypeConverters
import com.hanna.shulman.gantest.data.datasource.db.converters.IntListConverter

@TypeConverters(IntListConverter::class)
data class ShowCharacterSummary(
    val charId: Int,
    val name: String,
    val img: String,
    val seasonAppearance: List<Int>
)