package com.muazdev26.composenewsapp.domain.prefrences

import kotlinx.coroutines.flow.Flow


interface AppPreferencesHelper {

    suspend fun saveAppEntry()

    fun getAppEntry(): Flow<Boolean>
}