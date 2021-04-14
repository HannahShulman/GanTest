package com.hanna.shulman.gantest.data.datasource.db.converters

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringListConverterTest {

    private val converter = StringListConverter()

    @Test
    fun `GIVEN emptyList WHEN listToString THEN returned value is json empty array`() {
        val listValue = emptyList<String>()

        val listString = converter.listToString(listValue)

        assertThat(listString).isEqualTo("[]")
    }

    @Test
    fun `GIVEN not empty list WHEN listToString THEN returned value is json array`() {
        val listValue = listOf("firstElement", "secondElement")

        val listString = converter.listToString(listValue)

        assertThat(listString).isEqualTo("[\"firstElement\",\"secondElement\"]")
    }


    @Test
    fun `GIVEN list string WHEN stringToList THEN returned value is json empty array`() {
        val listStringValue = "[\"firstElement\",\"secondElement\"]"

        val listString = converter.stringToList(listStringValue)

        assertThat(listString).isEqualTo(listOf("firstElement", "secondElement"))
    }
}