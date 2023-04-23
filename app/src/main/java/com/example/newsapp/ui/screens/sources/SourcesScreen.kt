package com.example.newsapp.ui.screens.sources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Source
import com.example.newsapp.ui.common.LoadingScreen

@Composable
fun SourcesScreen(viewModel: SourcesViewModel = hiltViewModel()) {
    val sourcesState = viewModel.state.collectAsState()
    val onToggleSourceSelection: (Boolean, String) -> Unit = { isSelected, sourceId ->
        viewModel.toggleSourceSelection(isSelected, sourceId)
    }

    when (val stateValue = sourcesState.value) {
        is SourcesState.Loading -> {
            LoadingScreen()
        }

        is SourcesState.Error -> {
            Text(text = "Error..", modifier = Modifier.fillMaxSize())
        }

        is SourcesState.Empty -> {
            Text(text = "Empty..", modifier = Modifier.fillMaxSize())
        }

        is SourcesState.Success -> {
            SourcesList(
                sources = stateValue.sources,
                onToggleSourceSelection = onToggleSourceSelection
            )
        }
    }
}

@Composable
fun SourcesList(
    sources: List<Source>,
    onToggleSourceSelection: (Boolean, String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(items = sources) {
            SourceItem(
                source = it,
                onToggleSourceSelection = onToggleSourceSelection
            )
        }
    }
}

@Composable
fun SourceItem(
    source: Source,
    onToggleSourceSelection: (Boolean, String) -> Unit,
) {
    val isChecked = remember {
        mutableStateOf(source.isSelected)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = source.name,
            fontSize = 16.sp, fontWeight = FontWeight.Bold
        )
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
                onToggleSourceSelection(it, source.id)
            }
        )
    }
}

//@Preview
//@Composable
//fun SourceItemPreview() {
//    SourceItem(Source("abc", "ABC", false), )
//}
//
//@Preview
//@Composable
//fun SourcesScreenPreview() {
//    SourcesScreen()
//}