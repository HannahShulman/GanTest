package com.hanna.shulman.gantest.data.mappers

import com.hanna.shulman.gantest.domain.model.dto.ShowCharacterDto
import com.hanna.shulman.gantest.domain.model.entities.ShowCharacter
import com.hanna.shulman.gantest.domain.model.entities.Status

object ShowCharacterMapper {

    fun transform(dto: ShowCharacterDto): ShowCharacter {
        return ShowCharacter(
            charId = dto.charId,
            name = dto.name,
            img = dto.img,
            occupation = dto.occupation,
            status = Status.getStatusByValue(dto.status),
            nickname = dto.nickname,
            seasonAppearance = dto.appearance
        )
    }
}