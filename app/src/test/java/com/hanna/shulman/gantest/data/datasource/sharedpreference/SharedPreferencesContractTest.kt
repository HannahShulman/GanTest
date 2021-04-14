package com.hanna.shulman.gantest.data.datasource.sharedpreference

import android.content.Context
import android.os.Build
import androidx.preference.PreferenceManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.*

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class SharedPreferencesContractTest {

    private lateinit var sharedPreferencesContract: SharedPreferencesContract

    private val context: Context by lazy { InstrumentationRegistry.getInstrumentation().context }

    @Before
    fun reset() {
        sharedPreferencesContract =
            SharedPreferencesContract(//re-init sp for each test to start with clear data
                PreferenceManager.getDefaultSharedPreferences(context)
            )
    }

    @Test
    fun `GIVEN init sharedPreference WHEN lastFetched THEN lastFetched is 0`() {
        val givenContract = sharedPreferencesContract

        val lastFetched = givenContract.lastFetched

        assertThat(lastFetched).isEqualTo(0)
    }

    @Test
    fun `GIVEN a time WHEN lastFetched is set THEN lastFetched is given time`() {
        val givenTime: Long by lazy { Date().time }

        sharedPreferencesContract.lastFetched = givenTime

        assertThat(sharedPreferencesContract.lastFetched).isEqualTo(givenTime)
    }
}