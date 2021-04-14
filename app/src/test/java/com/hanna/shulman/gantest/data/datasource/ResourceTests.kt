package com.hanna.shulman.gantest.data.datasource

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ResourceTests {

    @Test
    fun `GIVEN no data WHEN create a loading resource with no data, THEN return loading resource with no data`() {
        val givenData = null

        val resource = Resource.loading(givenData)

        assertThat(resource).isEqualTo(Resource(Status.LOADING, null, null))
    }

    @Test
    fun `GIVEN data WHEN create a loading resource THEN return resource with data `() {
        val givenData = "data"

        val resource = Resource.loading(givenData)

        assertThat(resource).isEqualTo(Resource(Status.LOADING, "data", null))
    }

    @Test
    fun `GIVEN no data WHEN create an error resource THEN return resource with no data`() {
        val givenData = null

        val resource = Resource.error("error message", givenData)

        assertThat(resource).isEqualTo(Resource(Status.ERROR, null, "error message"))
    }

    @Test
    fun `GIVEN an error message WHEN create an error resource THEN return resource with given message`() {
        val givenMassage = "Given error message"

        val resource = Resource.error(givenMassage, null)

        assertThat(resource).isEqualTo(Resource(Status.ERROR, null, "Given error message"))
    }

    @Test
    fun `GIVEN data WHEN create an error resource THEN return resource with data`() {
        val givenData = "data"

        val resource = Resource.error("", givenData)

        assertThat(resource).isEqualTo(Resource(Status.ERROR, givenData, ""))
    }

    @Test
    fun `GIVEN no data WHEN create a success resource THEN return resource with no data`() {
        val givenData = null

        val resource = Resource.success(givenData)

        assertThat(resource).isEqualTo(Resource(Status.SUCCESS, givenData, null))
    }

    @Test
    fun `GIVEN data WHEN create a success resource THEN return resource with data`() {
        val givenData = "DATA"

        val resource = Resource.success(givenData)

        assertThat(resource).isEqualTo(Resource(Status.SUCCESS, "DATA", null))
    }
}