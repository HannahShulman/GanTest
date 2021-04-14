package com.hanna.shulman.gantest.domain.usecases

import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import javax.inject.Inject

class GetCharacterById @Inject constructor(
    private val repository: CharactersRepository
){
    operator fun invoke(charId: Int) = repository.getCharacterById(charId)
}