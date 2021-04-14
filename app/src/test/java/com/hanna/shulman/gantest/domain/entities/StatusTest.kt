package com.hanna.shulman.gantest.domain.entities

import com.google.common.truth.Truth.assertThat
import com.hanna.shulman.gantest.domain.model.entities.Status
import org.junit.Test

class StatusTest {

    @Test
    fun `GIVEN Presumed dead as status string WHEN getStatusByValue THEN return Status_PRESUMED_DEAD`() {
        val givenStatusString = "Presumed dead"

        val status = Status.getStatusByValue(givenStatusString)

        assertThat(status).isEqualTo(Status.PRESUMED_DEAD)
    }

    @Test
    fun `GIVEN Alive as status string WHEN getStatusByValue THEN return Status_ALIVE`() {
        val givenStatusString = "Alive"

        val status = Status.getStatusByValue(givenStatusString)

        assertThat(status).isEqualTo(Status.ALIVE)
    }

    @Test
    fun `GIVEN Alive as status string WHEN getStatusByValue THEN return Status_DECEASED`() {
        val givenStatusString = "Deceased"

        val status = Status.getStatusByValue(givenStatusString)

        assertThat(status).isEqualTo(Status.DECEASED)
    }

    @Test
    fun `GIVEN Unknown as status string WHEN getStatusByValue THEN return Status_UNKNOWN`() {
        val givenStatusString = "Unknown"

        val status = Status.getStatusByValue(givenStatusString)

        assertThat(status).isEqualTo(Status.UNKNOWN)
    }

    @Test
    fun `GIVEN Undefined status string WHEN getStatusByValue THEN return Status_UNKNOWN`() {
        val givenStatusString = "Undefined"

        val status = Status.getStatusByValue(givenStatusString)

        assertThat(status).isEqualTo(Status.UNKNOWN)
    }
}