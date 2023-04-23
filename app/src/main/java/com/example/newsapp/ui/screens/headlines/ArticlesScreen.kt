package com.example.newsapp.ui.screens.headlines

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.domain.model.Article
import com.example.newsapp.ui.common.LoadingScreen

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel = hiltViewModel(),
    onArticleClicked: (Article) -> Unit,
) {
    val uiState = viewModel.state.collectAsState()

    when (val state = uiState.value) {
        is ArticlesState.Loading -> {
            LoadingScreen()
        }

        is ArticlesState.Error -> {
            Text(text = "Error..", modifier = Modifier.fillMaxSize())
        }

        is ArticlesState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Please add news sources",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        is ArticlesState.Success -> {
            ArticlesList(
                articlesList = state.articles,
                onArticleClicked = onArticleClicked
            )
        }
    }
}

@Composable
fun ArticlesList(
    articlesList: List<Article>,
    onArticleClicked: (Article) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        itemsIndexed(items = articlesList) { index, item ->
            Box(modifier = Modifier.clickable { onArticleClicked(item) }) {
                ArticleItem(articleItem = item)
            }
            Spacer(modifier = Modifier.size(10.dp))
            if (index < articlesList.lastIndex) {
                Divider(color = Color.Black, thickness = 2.dp)
            }
        }
    }
}

@Composable
fun ArticleItem(articleItem: Article) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            model = articleItem.articleImageUrl,
            contentDescription = null
        )
        Text(
            text = articleItem.author,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = articleItem.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold

        )
        Text(
            text = articleItem.description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal

        )

    }
}