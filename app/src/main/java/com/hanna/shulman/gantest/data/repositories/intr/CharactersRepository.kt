package com.hanna.shulman.gantest.data.repositories.intr

import com.hanna.shulman.gantest.data.datasource.Resource
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharactersSummary(): Flow<Resource<List<ShowCharacterSummary>>>
    fun getLocalCharactersSummary(): Flow<List<ShowCharacterSummary>>
    fun getCharacterById(charId: Int): Flow<ShowCharacter>
}