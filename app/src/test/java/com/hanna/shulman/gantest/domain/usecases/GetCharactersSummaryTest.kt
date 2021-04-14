package com.hanna.shulman.gantest.domain.usecases

import com.hanna.shulman.gantest.data.datasource.Resource
import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flow
import org.junit.Test

class GetCharactersSummaryTest {


    private val repository: CharactersRepository = mock {
        onBlocking { getCharactersSummary() }.doReturn(flow { Resource.success(emptyList<ShowCharacterSummary>()) })
    }

    @Test
    fun `GIVEN GetCharactersSummary usecase WHEN invoked THEN verify repository getCharacters is called`() {
        val givenUsecase = GetCharactersSummary(repository)

        givenUsecase.invoke()

        verify(repository).getCharactersSummary()
    }
}