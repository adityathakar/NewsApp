package com.example.newsapp.ui.screens.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetSourcesUseCase
import com.example.domain.usecase.ToggleSourceSelectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val getSourcesUseCase: GetSourcesUseCase,
    private val toggleSourceSelectionUseCase: ToggleSourceSelectionUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<SourcesState>(SourcesState.Loading)

    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            runCatching {
                getSourcesUseCase.execute().collect {
                    _state.value = SourcesState.Success(it)
                }
            }.onFailure {
                _state.value = SourcesState.Error
            }
        }
    }

    fun toggleSourceSelection(isSelected: Boolean, sourceId: String) {
        viewModelScope.launch {
            toggleSourceSelectionUseCase.execute(isSelected, sourceId)
        }
    }
}