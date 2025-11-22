package com.konkuk.artium.data.repository

import com.konkuk.artium.data.dto.request.CommunityPostRequestDto
import com.konkuk.artium.data.dto.response.CommentResponseDto
import com.konkuk.artium.data.dto.response.CommunityDetailResponseDto
import com.konkuk.artium.data.dto.response.CommunityItemDto

interface CommunityRepository {
    suspend fun write(dto: CommunityPostRequestDto): CommunityDetailResponseDto
    suspend fun getList(): List<CommunityItemDto>
    suspend fun getDetail(id: Int): CommunityDetailResponseDto
    suspend fun update(id: Int, dto: CommunityPostRequestDto): CommunityDetailResponseDto
    suspend fun delete(id: Int)

    suspend fun writeComment(id: Int, content: String): CommentResponseDto
    suspend fun deleteComment(commentId: Int)
}

