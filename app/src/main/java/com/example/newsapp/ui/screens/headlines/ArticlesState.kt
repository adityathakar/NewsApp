package com.example.newsapp.ui.screens.headlines

import com.example.domain.model.Article

sealed class ArticlesState {

    object Loading: ArticlesState()

    object Error: ArticlesState()

    data class Success(val articles: List<Article>): ArticlesState()
}
