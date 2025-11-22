package com.konkuk.artium.ui.feature.community.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.artium.data.dto.request.CommunityPostRequestDto
import com.konkuk.artium.data.repository.CommunityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FreeWriteViewModel @Inject constructor(
    private val repository: CommunityRepository
) : ViewModel() {

    private val _successPostId = MutableStateFlow<Int?>(null)
    val successPostId = _successPostId.asStateFlow()

    fun write(title: String, content: String, category: String) {
        viewModelScope.launch {
            runCatching {
                repository.write(
                    CommunityPostRequestDto(
                        title = title,
                        content = content,
                        category = category
                    )
                )
            }.onSuccess {
                _successPostId.value = it.id
            }
        }
    }

    fun update(id: Int, title: String, content: String, category: String) {
        viewModelScope.launch {
            runCatching {
                repository.update(
                    id,
                    CommunityPostRequestDto(title, content, category)
                )
            }.onSuccess {
                _successPostId.value = it.id
            }
        }
    }
}

