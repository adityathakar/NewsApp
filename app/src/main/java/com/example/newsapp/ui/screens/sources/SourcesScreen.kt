package com.example.newsapp.ui.screens.sources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SourcesScreen() {
    SourcesList(sources = items)
}

@Composable
fun SourcesList(sources: List<SourceItemModel>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = sources) {
            SourceItem(sourceItemModel = it)
        }
    }
}

@Composable
fun SourceItem(sourceItemModel: SourceItemModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = sourceItemModel.sourceTitle,
            fontSize = 12.sp, fontWeight = FontWeight.Bold
        )
        Checkbox(
            checked = sourceItemModel.isChecked,
            onCheckedChange = {}
        )
    }
}

data class SourceItemModel(
    val sourceTitle: String,
    val isChecked: Boolean,
)

val items = listOf(
    SourceItemModel("ABC", true),
    SourceItemModel("CNN", false),
    SourceItemModel("Republic", true),
    SourceItemModel("Aaj Tak", false),
    SourceItemModel("Zee News", true),
    SourceItemModel("Nine News", false),
    SourceItemModel("Aljazeera", true),
    SourceItemModel("Seven News", false),
)

@Preview
@Composable
fun SourceItemPreview() {
    SourceItem(SourceItemModel("ABC", true))
}

@Preview
@Composable
fun SourcesScreenPreview() {
    SourcesScreen()
}