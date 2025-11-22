package com.konkuk.artium.ui.feature.community.viewmodel

data class CommunityUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val posts: List<CommunityItemUiModel> = emptyList()
)

data class CommunityItemUiModel(
    val id: Int,
    val title: String,
    val content: String,
    val category: String,
    val createdAt: String,
    val commentCount: Int
)

