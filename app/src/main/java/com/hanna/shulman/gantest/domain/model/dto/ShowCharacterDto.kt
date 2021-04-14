package com.hanna.shulman.gantest.domain.model.dto

data class ShowCharacterDto(
    val charId: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String,
    val betterCallSaulAppearance: List<Int>
)