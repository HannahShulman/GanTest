package com.hanna.shulman.gantest

import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacterSummary
import com.hanna.shulman.gantest.domain.model.entities.Status

object MockData {
    val showCharacter: ShowCharacter
        get() = ShowCharacter(
            charId = 3,
            name = "name",
            occupation = listOf("Teacher"),
            img = "url",
            status = Status.ALIVE,
            nickname = "Nickname",
            seasonAppearance = listOf(1, 3, 4)
        )

    val showCharacterSummary: ShowCharacterSummary
        get() = ShowCharacterSummary(
            charId = 3,
            name = "name",
            img = "url",
            seasonAppearance = listOf()
        )
    val showCharacterSummaryList: List<ShowCharacterSummary>
        get() = listOf(
            showCharacterSummary,
            showCharacterSummary.copy(name = "name2", seasonAppearance = listOf(1, 2)),
            showCharacterSummary.copy(name = "name3", seasonAppearance = listOf(2, 4)),
            showCharacterSummary.copy(name = "abcde", seasonAppearance = listOf(3, 4, 5)),
        )
}