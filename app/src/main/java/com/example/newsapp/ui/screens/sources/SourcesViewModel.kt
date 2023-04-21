package com.example.newsapp.ui.screens.sources

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val getSourcesUseCase: GetSourcesUseCase
) :
    ViewModel() {

    private val sourcesStateFlow = MutableStateFlow<SourcesState>(SourcesState.Loading)

    val sourcesUiStateFlow = sourcesStateFlow.asStateFlow()
    init {

        viewModelScope.launch {
            runCatching {
                val list = getSourcesUseCase.execute()
                Log.d("Adi", "Sources: $list")
                sourcesStateFlow.value = SourcesState.Success(list)
            }.onFailure {
                Log.d("Adi", "Error: $it")
            }
        }
    }
}