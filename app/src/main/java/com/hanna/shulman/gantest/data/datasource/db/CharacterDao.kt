package com.hanna.shulman.gantest.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCharacters(list: List<ShowCharacter>)

    @Query("SELECT charId, name, img, seasonAppearance FROM ShowCharacter")
    fun getCharactersSummary(): Flow<List<ShowCharacterSummary>>

    @Query("SELECT * FROM ShowCharacter WHERE charId =:id")
    fun getCharacterById(id: Int): Flow<ShowCharacter>
}