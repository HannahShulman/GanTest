package com.hanna.shulman.gantest.data.datasource.db.converters

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IntListConverterTest {

    private val converter = IntListConverter()

    @Test
    fun `GIVEN emptyList WHEN listToString THEN returned value is json empty array`() {
        val listValue = emptyList<Int>()

        val listString = converter.listToString(listValue)

        assertThat(listString).isEqualTo("[]")
    }

    @Test
    fun `GIVEN not empty list WHEN listToString THEN returned value is json array`() {
        val listValue = listOf(1, 2)

        val listString = converter.listToString(listValue)

        assertThat(listString).isEqualTo("[1,2]")
    }


    @Test
    fun `GIVEN list string WHEN stringToList THEN returned value is json empty array`() {
        val listStringValue = "[1,2]"

        val listString = converter.stringToIntList(listStringValue)

        assertThat(listString).isEqualTo(listOf(1, 2))
    }
}