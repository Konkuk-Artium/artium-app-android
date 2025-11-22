package com.konkuk.artium.ui.feature.community.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.artium.data.dto.mapper.toUiModel
import com.konkuk.artium.data.repository.CommunityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val repo: CommunityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CommunityUiState())
    val uiState = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            runCatching {
                repo.getList()
            }.onSuccess { list ->
                _uiState.value = _uiState.value.copy(
                    posts = list.map { it.toUiModel() },
                    isLoading = false
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    error = it.message,
                    isLoading = false
                )
            }
        }
    }
}
