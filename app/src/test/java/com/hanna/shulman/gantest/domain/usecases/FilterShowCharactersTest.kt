package com.hanna.shulman.gantest.domain.usecases

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.MockData
import com.hanna.shulman.gantest.MockData.showCharacterSummaryList
import com.hanna.shulman.gantest.data.datasource.Resource
import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import com.hanna.shulman.gantest.presentation.viewmodels.CharactersFilter
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class FilterShowCharactersTest {

    val showCharacters = MutableStateFlow(showCharacterSummaryList)
    private val repository: CharactersRepository = mock {
        onBlocking { getLocalCharactersSummary() } doReturn (showCharacters)
        onBlocking { getCharactersSummary() }.doReturn(flow {
            Resource.success(
                listOf(
                    MockData.showCharacterSummary
                )
            )
        })
    }


    @Test
    fun `GIVEN CharactersFilter usecase WHEN filterNameSeq is empty, and FilterShowCharacters invoked THEN return expected elements`() {
        runBlocking {
            val givenFilter = CharactersFilter("", listOf(2))
            val givenUsecase = FilterShowCharacters(repository)

            givenUsecase(givenFilter).test {
                assertThat(expectItem()[0].name).isEqualTo("name")
            }
        }
    }

    @Test
    fun `GIVEN CharactersFilter usecase WHEN filterNameSeq is not empty, and FilterShowCharacters invoked THEN return expected elements`() {
        runBlocking {
            val givenFilter = CharactersFilter("na", listOf(2))
            val givenUsecase = FilterShowCharacters(repository)

            givenUsecase(givenFilter).test {
                val item = expectItem()
                assertThat(item.size).isEqualTo(3)
                assertThat(item[0].name).isEqualTo("name")
            }
        }
    }

    @Test
    fun `GIVEN CharactersFilter usecase WHEN episodes not empty, and FilterShowCharacters invoked THEN return expected elements`() {
        runBlocking {
            val givenFilter = CharactersFilter("na", listOf(4))
            val givenUsecase = FilterShowCharacters(repository)

            givenUsecase(givenFilter).test {
                val item = expectItem()
                assertThat(item.size).isEqualTo(4)
                assertThat(item[0].name).isEqualTo("name")
            }
        }
    }
}