package com.konkuk.artium.ui.feature.archive.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.artium.data.dto.mapper.toMyWorkItem
import com.konkuk.artium.data.dto.mapper.toRecentWorkItem
import com.konkuk.artium.data.repository.ArchiveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val repository: ArchiveRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArchiveUiState())
    val uiState: StateFlow<ArchiveUiState> = _uiState.asStateFlow()

    fun loadArchives() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                // Repository가 이미 List<ArchiveResponseDto>를 반환함
                val response = repository.getArchiveList()

                // 변환
                val recentList = response.map { it.toRecentWorkItem() }
                val myWorkList = response.map { it.toMyWorkItem() }

                // 상태 업데이트
                _uiState.value = _uiState.value.copy(
                    recentWorks = recentList,
                    myWorks = myWorkList,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "알 수 없는 오류 발생"
                )
            }
        }
    }
}
