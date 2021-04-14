package com.hanna.shulman.gantest.data.datasource.sharedpreference

import android.content.SharedPreferences
import com.hanna.shulman.gantest.OpenForTesting
import javax.inject.Inject

//TESTS-V
@OpenForTesting
class SharedPreferencesContract @Inject constructor(private val sharedPreferences: SharedPreferences) {

    @Suppress("PrivatePropertyName")
    private val CHARACTERS_REQUEST_TIME = "characters_request_time"

    var lastFetched: Long = 0L
        set(value) {
            field = value
            sharedPreferences.edit().putLong(CHARACTERS_REQUEST_TIME, value).apply()
        }
        get() = sharedPreferences.getLong(CHARACTERS_REQUEST_TIME, 0L)
}