package com.hanna.shulman.gantest.data.mappers

import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.domain.model.dto.ShowCharacterDto
import com.hanna.shulman.gantest.domain.model.entities.Status
import org.junit.Test

class ShowCharacterMapperTest {

    private val givenDto = ShowCharacterDto(
        charId = 12,
        name = "name",
        birthday = "01/03/1992",
        occupation = emptyList(),
        img = "",
        status = "",
        appearance = listOf(),
        betterCallSaulAppearance = emptyList(),
        nickname = "",
        portrayed = ""
    )

    @Test
    fun `GIVEN dto WHEN transformed THEN id is equal to dto id`() {
        val setDto = givenDto.copy(charId = 12)

        val character = ShowCharacterMapper.transform(setDto)

        assertThat(character.charId).isEqualTo(12)
    }

    @Test
    fun `GIVEN dto WHEN transformed THEN name is equal to dto name`() {
        val setDto = givenDto.copy(name = "name")

        val character = ShowCharacterMapper.transform(setDto)

        assertThat(character.name).isEqualTo("name")
    }

    @Test
    fun `GIVEN dto WHEN transformed THEN occupation is equal to dto occupation`() {
        val setDto = givenDto.copy(occupation = listOf("Teacher"))

        val character = ShowCharacterMapper.transform(setDto)

        assertThat(character.occupation).isEqualTo(listOf("Teacher"))
    }

    @Test
    fun `GIVEN dto WHEN transformed THEN image is equal to dto occupation`() {
        val setDto = givenDto.copy(img = "This is a url")

        val character = ShowCharacterMapper.transform(setDto)

        assertThat(character.img).isEqualTo("This is a url")
    }

    @Test
    fun `GIVEN dto WHEN transformed THEN status is equal to dto occupation`() {
        val setDto = givenDto.copy(status = "Alive")

        val character = ShowCharacterMapper.transform(setDto)

        assertThat(character.status).isEqualTo(Status.ALIVE)
    }

    @Test
    fun `GIVEN dto WHEN transformed THEN nickname is equal to dto occupation`() {
        val setDto = givenDto.copy(nickname = "This is my nickname")

        val character = ShowCharacterMapper.transform(setDto)

        assertThat(character.nickname).isEqualTo("This is my nickname")
    }

    @Test
    fun `GIVEN dto WHEN transformed THEN seasonAppearance is equal to dto appearance`() {
        val setDto = givenDto.copy(appearance = listOf(1, 2, 3))

        val character = ShowCharacterMapper.transform(setDto)

        assertThat(character.seasonAppearance).isEqualTo(listOf(1, 2, 3))
    }
}