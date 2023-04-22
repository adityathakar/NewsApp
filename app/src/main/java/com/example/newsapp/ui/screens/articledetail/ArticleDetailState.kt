package com.example.newsapp.ui.screens.articledetail

sealed class ArticleDetailState {

    object Favourite: ArticleDetailState()

    object NotFavourite: ArticleDetailState()
}
