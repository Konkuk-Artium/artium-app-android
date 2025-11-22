package com.konkuk.artium.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CommunityPostRequestDto(
    val title: String,
    val content: String,
    val category: String
)
@Serializable
data class CommentRequestDto(
    val content: String
)

@Serializable
data class CommentCreateRequestDto(
    val content: String
)
@Serializable
data class CommunityEditRequestDto(
    val title: String,
    val content: String,
    val category: String
)

@Serializable
data class CommentWriteRequestDto(
    val content: String
)
