package com.hanna.shulman.gantest.presentation.viewmodels

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.OpenForTesting
import com.hanna.shulman.gantest.domain.usecases.FilterShowCharacters
import com.hanna.shulman.gantest.domain.usecases.GetCharactersSummary
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@OpenForTesting
class CharactersViewModelTest {

    val getCharactersSummary = mock<GetCharactersSummary> { on { invoke() } doReturn flow { } }
    val filterShowCharacters = mock<FilterShowCharacters> { on { invoke(any()) } doReturn flow { } }


    @Test
    fun `GIVEN viewModel WHEN setCharacterFilter with null THEN getCharactersSummary is invoked`() {
        runBlockingTest {
            val givenViewModel = CharactersViewModel(getCharactersSummary, filterShowCharacters)

            givenViewModel.setCharacterFilter(null)

            givenViewModel.charactersSummaryList.test {
                verify(getCharactersSummary).invoke()
            }
        }
    }

    @Test
    fun `GIVEN viewModel WHEN setCharacterFilter with filter THEN getCharactersSummary is invoked with correct values`() {
        runBlockingTest {
            val givenViewModel = CharactersViewModel(getCharactersSummary, filterShowCharacters)

            val filterCapture = argumentCaptor<CharactersFilter>()
            givenViewModel.setCharacterFilter(CharactersFilter("filter", emptyList()))

            givenViewModel.charactersSummaryList.test {

                verify(filterShowCharacters).invoke(filterCapture.capture())
                assertThat(filterCapture.firstValue.filterNameSeq).isEqualTo("filter")
            }
        }
    }
}