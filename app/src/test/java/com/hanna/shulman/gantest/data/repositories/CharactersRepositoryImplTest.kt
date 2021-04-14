package com.hanna.shulman.gantest.data.repositories

import android.os.Build
import android.text.format.DateUtils
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.MockData.showCharacter
import com.hanna.shulman.gantest.data.datasource.db.CharacterDao
import com.hanna.shulman.gantest.data.datasource.network.Api
import com.hanna.shulman.gantest.data.datasource.network.ApiResponse
import com.hanna.shulman.gantest.data.datasource.network.ApiSuccessResponse
import com.hanna.shulman.gantest.data.datasource.sharedpreference.SharedPreferencesContract
import com.hanna.shulman.gantest.data.repositories.impl.CharactersRepositoryImpl
import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import com.hanna.shulman.gantest.domain.model.dto.ShowCharacterDto
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.domain.model.entities.Status
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class CharactersRepositoryImplTest {

    private val localCharacters = MutableStateFlow<List<ShowCharacterSummary>>(emptyList())
    private val localCharacterById = MutableStateFlow(showCharacter)
    private val apiCharacters = MutableStateFlow<ApiResponse<List<ShowCharacterDto>>>(
        ApiSuccessResponse(
            emptyList()
        )
    )
    private val api = mock<Api> {
        onBlocking { getCharacters() } doReturn apiCharacters
    }
    private val dao = mock<CharacterDao> {
        on { getCharactersSummary() } doReturn localCharacters
        on { getCharacterById(any()) } doReturn localCharacterById
    }
    private val spContract = mock<SharedPreferencesContract> { }

    private lateinit var repositoryImpl: CharactersRepository

    @Before
    fun setup() {
        repositoryImpl = CharactersRepositoryImpl(api, dao, spContract)
    }

    @Test
    fun `GIVEN repository WHEN getCharactersSummary and should not fetch from network THEN api never calls getCharacters`() {
        whenever(spContract.lastFetched).thenReturn(Date().time.plus(DateUtils.DAY_IN_MILLIS.times(5)))

        runBlocking {
            repositoryImpl.getCharactersSummary().toList()

            verify(api, never()).getCharacters()
            verify(dao).getCharactersSummary()
            verify(dao, never()).insertCharacters(emptyList())
        }
    }

    @Test
    fun `GIVEN repository WHEN getCharactersSummary and should not fetch from network THEN dao calls getCharactersSummary once`() {
        whenever(spContract.lastFetched).thenReturn(Date().time.plus(DateUtils.DAY_IN_MILLIS.times(5)))

        runBlocking {
            repositoryImpl.getCharactersSummary().toList()

            verify(dao).getCharactersSummary()
        }
    }

    @Test
    fun `GIVEN repository WHEN getCharactersSummary and should not fetch from network THEN dao never calls insertCharacters`() {
        whenever(spContract.lastFetched).thenReturn(Date().time.plus(DateUtils.DAY_IN_MILLIS.times(5)))

        runBlocking {
            repositoryImpl.getCharactersSummary().toList()

            verify(dao, never()).insertCharacters(emptyList())
        }
    }


    @Test
    fun `GIVEN repository WHEN getCharactersSummary and should fetch from network THEN api calls getCharacters`() {
        whenever(spContract.lastFetched).thenReturn(0)

        runBlocking {
            repositoryImpl.getCharactersSummary().test {
                expectItem()
                expectItem()
                expectItem()
                verify(api).getCharacters()
            }
        }
    }

    @Test
    fun `GIVEN repository WHEN getCharactersSummary and should fetch from network THEN dao calls getCharactersSummary twice`() {
        whenever(spContract.lastFetched).thenReturn(0)

        runBlocking {
            repositoryImpl.getCharactersSummary().test {
                expectItem()
                expectItem()
                expectItem()
                verify(dao, times(2)).getCharactersSummary()
            }
        }
    }

    @Test
    fun `GIVEN repository WHEN getCharactersSummary and should fetch from network THEN dao calls insertCharacters`() {
        whenever(spContract.lastFetched).thenReturn(0)

        runBlocking {
            repositoryImpl.getCharactersSummary().test {
                expectItem()
                expectItem()
                expectItem()
                verify(dao).insertCharacters(emptyList())
            }
        }
    }

    @Test
    fun `GIVEN repository WHEN getLocalCharactersSummary THEN dao calls insertCharacters`() {
        whenever(spContract.lastFetched).thenReturn(0)

        runBlocking {
            repositoryImpl.getLocalCharactersSummary().test {
                expectItem()
                verify(dao).getCharactersSummary()
            }
        }
    }

    @Test
    fun `GIVEN repository WHEN getCharacterById THEN dao calls getCharacterById`() {
        whenever(spContract.lastFetched).thenReturn(0)

        runBlocking {
            repositoryImpl.getCharacterById(3).test {
                expectItem()
                verify(dao).getCharacterById(3)
            }
        }
    }

    @Test
    fun `GIVEN repository WHEN getCharacterById THEN dao calls getCharacterById with correct id`() {
        whenever(spContract.lastFetched).thenReturn(0)

        val idCapture = argumentCaptor<Int>()
        runBlocking {
            repositoryImpl.getCharacterById(3).test {
                expectItem()
                verify(dao).getCharacterById(idCapture.capture())
                assertThat(idCapture.firstValue).isEqualTo(3)
            }
        }
    }
}