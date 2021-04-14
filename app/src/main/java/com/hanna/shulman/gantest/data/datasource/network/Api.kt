package com.hanna.shulman.gantest.data.datasource.network

import com.hanna.shulman.gantest.domain.model.dto.ShowCharacterDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Api {
    @GET("characters")
    fun getCharacters(): Flow<ApiResponse<List<ShowCharacterDto>>>
}