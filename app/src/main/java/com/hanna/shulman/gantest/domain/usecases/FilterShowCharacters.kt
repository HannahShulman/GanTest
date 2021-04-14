package com.hanna.shulman.gantest.domain.usecases

import com.hanna.shulman.gantest.OpenForTesting
import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.presentation.viewmodels.CharactersFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

@OpenForTesting
class FilterShowCharacters @Inject constructor(
    val repository: CharactersRepository
) {
    operator fun invoke(filter: CharactersFilter): Flow<List<ShowCharacterSummary>> {
        val episodes = listOf(1, 2, 3, 4, 5)
        return repository.getLocalCharactersSummary().map { list ->
            list.filter { character ->
                val contains = if (filter.filterNameSeq.isNotEmpty()) {
                    character.name.toLowerCase(Locale.getDefault())
                        .contains(filter.filterNameSeq.toLowerCase(Locale.getDefault()))
                } else {
                    false
                }
                contains || character.seasonAppearance.intersect(filter.filterEpisodes.takeIf { it.isNotEmpty() }
                    ?: episodes)
                    .isNotEmpty()
            }
        }
    }

}