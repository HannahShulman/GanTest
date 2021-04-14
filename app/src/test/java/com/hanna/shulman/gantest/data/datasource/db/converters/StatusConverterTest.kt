package com.hanna.shulman.gantest.data.datasource.db.converters

import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.domain.model.entities.Status
import org.junit.Test

class StatusConverterTest {

    private val converter = StatusConverter()

    @Test
    fun `GIVEN PRESUMED_DEAD WHEN statusToString THEN returned value is Presumed dead`() {
        val givenStatus = Status.PRESUMED_DEAD

        val statusString = converter.statusToString(givenStatus)

        assertThat(statusString).isEqualTo("Presumed dead")
    }

    @Test
    fun `GIVEN ALIVE WHEN statusToString THEN returned value is Alive`() {
        val statusString = converter.statusToString(Status.ALIVE)

        assertThat(statusString).isEqualTo("Alive")
    }

    @Test
    fun `GIVEN DECEASED WHEN statusToString THEN returned value is Deceased`() {
        val statusString = converter.statusToString(Status.DECEASED)

        assertThat(statusString).isEqualTo("Deceased")
    }

    @Test
    fun `GIVEN UNKNOWN WHEN statusToString THEN returned value is Unknown`() {
        val statusString = converter.statusToString(Status.UNKNOWN)

        assertThat(statusString).isEqualTo("Unknown")
    }

    @Test
    fun `GIVEN Presumed dead WHEN stringToStatus THEN returned value is PRESUMED_DEAD`() {
        val status = converter.stringToStatus("Presumed dead")

        assertThat(status).isEqualTo(Status.PRESUMED_DEAD)
    }

    @Test
    fun `GIVEN Alive stringToStatus THEN returned value is ALIVE`() {
        val status = converter.stringToStatus("Alive")

        assertThat(status).isEqualTo(Status.ALIVE)
    }

    @Test
    fun `GIVEN Deceased WHEN stringToStatus THEN returned value is DECEASED`() {
        val status = converter.stringToStatus("Deceased")

        assertThat(status).isEqualTo(Status.DECEASED)
    }

    @Test
    fun `GIVEN Unknown WHEN stringToStatus THEN returned value is UNKNOWN`() {
        val status = converter.stringToStatus("Unknown")

        assertThat(status).isEqualTo(Status.UNKNOWN)
    }

    @Test
    fun `GIVEN an undefined status string WHEN stringToStatus THEN returned value is UNKNOWN`() {
        val status = converter.stringToStatus("undefined")

        assertThat(status).isEqualTo(Status.UNKNOWN)
    }
}