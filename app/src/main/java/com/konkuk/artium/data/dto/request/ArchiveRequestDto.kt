package com.konkuk.artium.data.dto.request

import kotlinx.serialization.Serializable

/**
 * 서버로 "글쓰기 / 작품 등록"할 때 보내는 DTO
 */
@Serializable
data class ArchiveRequestDto(
    val title: String = "",
    val date: String = "",
    val review: String = "",
    val rating: Float = 0f,
    val imageUrl: String = "",
)
