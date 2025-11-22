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
class QnaViewModel @Inject constructor(
    private val repo: CommunityRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<List<CommunityItemUiModel>>(emptyList())
    val posts = _posts.asStateFlow()

    fun load() {
        viewModelScope.launch {
            val result = repo.getList()
            _posts.value = result
                .filter { it.category == "Q&A" }
                .map { it.toUiModel() }
        }
    }
}
