package com.example.newsapp.ui.screens.saved

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Article
import com.example.newsapp.ui.screens.headlines.ArticlesList

@Composable
fun SavedArticlesScreen(
    viewModel: SavedArticlesViewModel = hiltViewModel(),
    onArticleClicked: (Article) -> Unit,
) {
    val uiState = viewModel.state.collectAsState()

    when (val state = uiState.value) {
        is SavedArticlesState.Loading -> {
            Text(text = "Loading..", modifier = Modifier.fillMaxSize())
        }

        is SavedArticlesState.Error -> {
            Text(text = "Error..", modifier = Modifier.fillMaxSize())
        }

        is SavedArticlesState.Empty -> {
            Text(text = "Empty..", modifier = Modifier.fillMaxSize())
        }

        is SavedArticlesState.Success -> {
            ArticlesList(
                articlesList = state.articles,
                onArticleClicked = onArticleClicked
            )
        }
    }
}