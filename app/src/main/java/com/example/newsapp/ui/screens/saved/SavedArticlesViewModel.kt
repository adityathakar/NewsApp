package com.example.newsapp.ui.screens.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetSavedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedArticlesViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SavedArticlesState>(SavedArticlesState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getSavedArticlesUseCase.execute().collect {
                _state.value = SavedArticlesState.Success(it)
            }
        }
    }
}