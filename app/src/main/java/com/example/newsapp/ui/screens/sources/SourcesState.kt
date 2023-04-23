package com.example.newsapp.ui.screens.sources

import com.example.domain.model.Source

sealed class SourcesState {

    object Loading : SourcesState()

    object Error : SourcesState()

    data class Success(val sources: List<Source>) : SourcesState()
}
