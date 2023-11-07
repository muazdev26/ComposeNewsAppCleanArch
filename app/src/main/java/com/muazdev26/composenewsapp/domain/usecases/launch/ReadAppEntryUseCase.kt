package com.muazdev26.composenewsapp.domain.usecases.launch

import com.muazdev26.composenewsapp.domain.prefrences.AppPreferencesHelper
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(
    private val appPreferencesHelper: AppPreferencesHelper
) {
    operator fun invoke(): Flow<Boolean> {
        return appPreferencesHelper.getAppEntry()
    }
}


