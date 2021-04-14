package com.hanna.shulman.gantest.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hanna.shulman.gantest.data.datasource.db.converters.IntListConverter
import com.hanna.shulman.gantest.data.datasource.db.converters.StatusConverter
import com.hanna.shulman.gantest.data.datasource.db.converters.StringListConverter

@Entity
@TypeConverters(
    StringListConverter::class,
    StatusConverter::class,
    IntListConverter::class
)
data class ShowCharacter(
    @PrimaryKey val charId: Int,
    val name: String,
    val occupation: List<String>,
    val img: String,
    val status: Status,
    val nickname: String,
    val seasonAppearance: List<Int>
)

//I have created this enum class to define the states/statuses of the characters.
//I know I have no contract with server side saying they wont add additional states, however, based
//on the nature of this field, where there are defined states, I have implemented as an enum.
//In case server side add additional states, these new states will be recognised as unknown,
// until app is upgraded,
enum class Status(val value: String) {
    PRESUMED_DEAD("Presumed dead"),
    ALIVE("Alive"),
    DECEASED("Deceased"),
    UNKNOWN("Unknown");

    companion object {
        fun getStatusByValue(value: String): Status {
            return values().firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}