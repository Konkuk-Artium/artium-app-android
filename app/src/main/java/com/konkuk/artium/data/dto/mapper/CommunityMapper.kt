package com.konkuk.artium.data.dto.mapper

import com.konkuk.artium.data.dto.response.CommunityDetailResponseDto
import com.konkuk.artium.data.dto.response.CommunityItemDto
import com.konkuk.artium.ui.feature.community.viewmodel.CommentUiModel
import com.konkuk.artium.ui.feature.community.viewmodel.CommunityItemUiModel
import com.konkuk.artium.ui.feature.community.viewmodel.PostDetailUiState

fun CommunityItemDto.toUiModel() = CommunityItemUiModel(
    id = id,
    title = title,
    content = content,
    category = category,
    createdAt = created_at,
    commentCount = comment_count
)

fun CommunityDetailResponseDto.toUiState() = PostDetailUiState(
    id = id,
    title = title,
    content = content,
    category = category,
    author = user_id,
    createdAt = created_at,
    likeCount = view_count,
    commentCount = comments.size,
    comments = comments.map {
        CommentUiModel(
            commentId = it.commentId,
            writer = it.writer ?: it.user_id.orEmpty(),
            content = it.content,
            createdAt = it.created_at
        )
    }
)