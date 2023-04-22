package com.example.newsapp.ui.screens.articledetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Article
import com.example.domain.usecase.IsArticleFavouriteUseCase
import com.example.domain.usecase.ToggleArticleFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val toggleArticleFavouriteUseCase: ToggleArticleFavouriteUseCase,
    private val isArticleFavouriteUseCase: IsArticleFavouriteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<ArticleDetailState>(ArticleDetailState.NotFavourite)

    val uiStateFlow = _state.asStateFlow()

    fun isArticleFavourite(article: Article) {
        viewModelScope.launch {
            isArticleFavouriteUseCase.execute(article).collect { isFavourite ->
                Log.d("Adi", "isArticleFavourite: $isFavourite")
                if (isFavourite) {
                    _state.value = ArticleDetailState.Favourite
                } else {
                    _state.value = ArticleDetailState.NotFavourite
                }
            }
        }
    }

    fun toggleArticleFavourite(isFavourite: Boolean, article: Article) {
        viewModelScope.launch {
            runCatching {
                toggleArticleFavouriteUseCase.execute(isFavourite, article)
            }.onFailure {
                Log.d("Adi", "toggleArticleFavourite Error: $it")
            }.onSuccess {
                Log.d("Adi", "toggleArticleFavourite: Success")
            }
        }
    }
}