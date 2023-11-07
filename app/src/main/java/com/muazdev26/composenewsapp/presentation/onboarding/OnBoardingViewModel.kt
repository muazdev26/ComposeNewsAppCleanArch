package com.muazdev26.composenewsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muazdev26.composenewsapp.domain.usecases.launch.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCases
) : ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SavAppEntry -> {
                viewModelScope.launch {
                    appEntryUseCase.saveAppEntryUseCase()
                }
            }
        }
    }
}