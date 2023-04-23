package com.example.newsapp.ui.screens.articledetail

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Article

@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailViewModel = hiltViewModel(),
    article: Article,
    onBackArrowClicked: () -> Unit
) {
    val uiState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.isArticleFavourite(article)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .clickable { onBackArrowClicked() }
                    .size(30.dp)
                    .align(CenterVertically),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = ""
            )
            Icon(
                modifier = Modifier
                    .clickable {
                        val isCurrentFavourite = uiState.value is ArticleDetailState.Favourite
                        viewModel.toggleArticleFavourite(!isCurrentFavourite, article)
                    }
                    .size(30.dp)
                    .align(CenterVertically),
                imageVector = if (uiState.value is ArticleDetailState.Favourite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(article.articleUrl)
            }
        }, update = {
            it.loadUrl(article.articleUrl)
        })
    }
}