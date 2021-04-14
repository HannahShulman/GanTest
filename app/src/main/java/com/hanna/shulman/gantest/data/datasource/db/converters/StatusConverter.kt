package com.hanna.shulman.gantest.data.datasource.db.converters

import androidx.room.TypeConverter
import com.hanna.shulman.gantest.domain.model.entities.Status
import org.jetbrains.annotations.TestOnly

//TESTS - V
class StatusConverter {

    @TypeConverter
    fun statusToString(status: Status): String = status.value

    @TypeConverter
    fun stringToStatus(value: String): Status =
        Status.getStatusByValue(value)
}