package com.example.newsapp.ui.screens.sources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Source

@Composable
fun SourcesScreen(viewModel: SourcesViewModel = hiltViewModel()) {
    val sourcesState = viewModel.sourcesUiStateFlow.collectAsState()

    when(val stateValue = sourcesState.value) {
        is SourcesState.Loading -> {
            Text(text = "Loading..", modifier = Modifier.fillMaxSize())
        }
        is SourcesState.Success -> {
            SourcesList(sources = stateValue.sources)
        }
    }
}

@Composable
fun SourcesList(sources: List<Source>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = sources) {
            SourceItem(source = it)
        }
    }
}

@Composable
fun SourceItem(source: Source) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = source.name,
            fontSize = 12.sp, fontWeight = FontWeight.Bold
        )
        Checkbox(
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Preview
@Composable
fun SourceItemPreview() {
    SourceItem(Source("abc", "ABC"))
}

@Preview
@Composable
fun SourcesScreenPreview() {
    SourcesScreen()
}