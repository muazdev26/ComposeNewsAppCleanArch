package com.muazdev26.composenewsapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muazdev26.composenewsapp.domain.usecases.bookmarks.BookMarkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val bookMarkUseCase: BookMarkUseCases
) : ViewModel() {

    private var _bookMarkState = mutableStateOf(BookMarkScreenState())
    val bookMarkState: State<BookMarkScreenState> = _bookMarkState

    init {
        getBookMarks()
    }

    private fun getBookMarks() = viewModelScope.launch {
        bookMarkUseCase.getBookMarksUseCase().collect {
            _bookMarkState.value = _bookMarkState.value.copy(bookmarkArticles = it?.asReversed() ?: emptyList())
        }
    }

}