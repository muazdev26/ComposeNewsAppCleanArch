package com.muazdev26.composenewsapp.domain.usecases.launch

import com.muazdev26.composenewsapp.domain.prefrences.AppPreferencesHelper

class SaveAppEntryUseCase(
    private val appPreferencesHelper: AppPreferencesHelper
) {
    suspend operator fun invoke() {
        appPreferencesHelper.saveAppEntry()
    }
}