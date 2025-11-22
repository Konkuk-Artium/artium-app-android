package com.konkuk.artium.data.dto.response

import kotlinx.serialization.Serializable

// 전체 목록 조회 item
@Serializable
data class CommunityItemDto(
    val id: Int,
    val title: String,
    val content: String,
    val category: String,
    val created_at: String,
    val comment_count: Int
)

// 전체 목록 wrapper (서버에서 list 로 내려줌)
@Serializable
data class CommunityListResponseDto(
    val list: List<CommunityItemDto>
)

// 상세 조회
@Serializable
data class CommunityDetailResponseDto(
    val id: Int,
    val title: String,
    val content: String,
    val category: String,
    val user_id: String,
    val view_count: Int,
    val created_at: String,
    val updated_at: String,
    val comments: List<CommentDto>,
    val is_mine: Boolean
)


// 댓글 DTO
@Serializable
data class CommentDto(
    val commentId: Int,
    val writer: String? = null,
    val user_id: String? = null,
    val content: String,
    val created_at: String
)

// 댓글 작성 응답
@Serializable
data class CommentResponseDto(
    val commentId: Int,
    val content: String,
    val created_at: String,
    val writer: String
)
