package com.hanna.shulman.gantest.domain.usecases

import com.hanna.shulman.gantest.OpenForTesting
import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import javax.inject.Inject

@OpenForTesting
class GetCharactersSummary @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke() = repository.getCharactersSummary()
}