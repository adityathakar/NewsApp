package com.example.newsapp.ui.screens.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ArticlesState>(ArticlesState.Loading)

    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            runCatching {
                val articles = getArticlesUseCase.execute()
                _state.value = if (articles.isEmpty()) {
                    ArticlesState.Empty
                } else {
                    ArticlesState.Success(articles)
                }
            }.onFailure {
                _state.value = ArticlesState.Error
            }
        }
    }
}