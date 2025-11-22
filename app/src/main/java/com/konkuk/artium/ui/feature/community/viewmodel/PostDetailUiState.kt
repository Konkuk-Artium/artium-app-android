package com.konkuk.artium.ui.feature.community.viewmodel

data class PostDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val category: String = "",
    val author: String = "",
    val createdAt: String = "",
    val likeCount: Int = 0,
    val commentCount: Int = 0,

    val comments: List<CommentUiModel> = emptyList()
)

data class CommentUiModel(
    val commentId: Int,
    val writer: String,
    val content: String,
    val createdAt: String
)

