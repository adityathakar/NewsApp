package com.example.newsapp.ui.screens.headlines

import android.util.Log
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

    private val articlesStateFlow = MutableStateFlow<ArticlesState>(ArticlesState.Loading)

    val articlesUiStateFlow = articlesStateFlow.asStateFlow()

    init {

        viewModelScope.launch {
            runCatching {
                val articles = getArticlesUseCase.execute()
                Log.d("Adi", "Articles: $articles")
                articlesStateFlow.value = ArticlesState.Success(articles)
            }.onFailure {
                Log.d("Adi", "Error: $it")
            }
        }
    }
}