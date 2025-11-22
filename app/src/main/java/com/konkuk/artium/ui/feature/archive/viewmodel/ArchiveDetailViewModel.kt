package com.konkuk.artium.ui.feature.archive.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.artium.data.dto.mapper.toUiState
import com.konkuk.artium.data.repository.ArchiveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Archive 상세 화면 전용 ViewModel
 */
@HiltViewModel
class ArchiveDetailViewModel @Inject constructor(
    private val repository: ArchiveRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val workId: Int = savedStateHandle["workId"] ?: 0

    private val _uiState = MutableStateFlow(MyArtworkDetailUiState())
    val uiState: StateFlow<MyArtworkDetailUiState> = _uiState.asStateFlow()

    init {
        loadArtworkDetail()
    }

    private fun loadArtworkDetail() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val result = repository.getArchiveDetail(workId)

                _uiState.value = result.toUiState()

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }
}
