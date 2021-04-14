package com.hanna.shulman.gantest.data.repositories.impl

import android.text.format.DateUtils
import com.hanna.shulman.gantest.data.datasource.FlowNetworkBoundResource
import com.hanna.shulman.gantest.data.datasource.Resource
import com.hanna.shulman.gantest.data.datasource.db.CharacterDao
import com.hanna.shulman.gantest.data.datasource.network.Api
import com.hanna.shulman.gantest.data.datasource.network.ApiResponse
import com.hanna.shulman.gantest.data.datasource.sharedpreference.SharedPreferencesContract
import com.hanna.shulman.gantest.data.mappers.ShowCharacterMapper
import com.hanna.shulman.gantest.data.repositories.intr.CharactersRepository
import com.hanna.shulman.gantest.domain.model.dto.ShowCharacterDto
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

//TESTS-V
class CharactersRepositoryImpl @Inject constructor(
    val api: Api,
    val dao: CharacterDao,
    private val spContract: SharedPreferencesContract
) : CharactersRepository {

    //this method returns the summary/the data requested for the list.
    //In this case the returned result
    // is a a list of the character summary requested to display on the first page.
    override fun getCharactersSummary(): Flow<Resource<List<ShowCharacterSummary>>> {
        return object :
            FlowNetworkBoundResource<List<ShowCharacterSummary>, List<ShowCharacterDto>>() {

            override suspend fun saveNetworkResult(item: List<ShowCharacterDto>) {
                dao.insertCharacters(item.map { ShowCharacterMapper.transform(it) })
            }

            override fun shouldFetch(): Boolean {
                //fetch from network once a day. this data doesn't change often,
                // so no need to fetch every time the user enters the app
                return spContract.lastFetched.plus(DateUtils.DAY_IN_MILLIS) < Date().time
            }

            override suspend fun loadFromDb(): Flow<List<ShowCharacterSummary>> {
                return dao.getCharactersSummary()
            }

            override suspend fun fetchFromNetwork(): Flow<ApiResponse<List<ShowCharacterDto>>> {
                return api.getCharacters()
            }
        }.asFlow()
    }

    override fun getLocalCharactersSummary(): Flow<List<ShowCharacterSummary>> {
        return dao.getCharactersSummary()
    }

    override fun getCharacterById(charId: Int): Flow<ShowCharacter> {
        return dao.getCharacterById(charId)
    }
}