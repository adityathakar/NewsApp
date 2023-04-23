package com.example.newsapp.ui.screens.saved

import com.example.domain.model.Article

sealed class SavedArticlesState {

    object Loading : SavedArticlesState()

    object Error : SavedArticlesState()

    object Empty : SavedArticlesState()

    data class Success(val articles: List<Article>) : SavedArticlesState()
}
