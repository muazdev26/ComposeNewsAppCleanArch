package com.muazdev26.composenewsapp.data.prefrences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.muazdev26.composenewsapp.data.prefrences.PreferencesKeys.appEntry
import com.muazdev26.composenewsapp.domain.prefrences.AppPreferencesHelper
import com.muazdev26.composenewsapp.util.Consts.APP_ENTRY_KEY
import com.muazdev26.composenewsapp.util.Consts.PREF_NAME
import kotlinx.coroutines.flow.map

class AppPreferencesHelperImpl(
    private val context: Context
) : AppPreferencesHelper {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { prefs ->
            prefs[appEntry] = true
        }
    }

    override fun getAppEntry() = context.dataStore.data.map { prefs ->
        prefs[appEntry] ?: false
    }

}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREF_NAME)

private object PreferencesKeys {
    val appEntry = booleanPreferencesKey(APP_ENTRY_KEY)
}