package com.example.tbcacademyhomework.domain.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DatastorePreferenceKeys {
    val EMAIL = stringPreferencesKey("email")
    val TOKEN = stringPreferencesKey("token")
    val SHOULD_REMEMBER = booleanPreferencesKey("should_remember")
}