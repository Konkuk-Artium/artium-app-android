package com.konkuk.artium.data.repositoryImpl

import com.konkuk.artium.data.api.CommunityService
import com.konkuk.artium.data.dto.request.CommentWriteRequestDto
import com.konkuk.artium.data.dto.request.CommunityPostRequestDto
import com.konkuk.artium.data.dto.response.CommentResponseDto
import com.konkuk.artium.data.dto.response.CommunityDetailResponseDto
import com.konkuk.artium.data.dto.response.CommunityItemDto
import com.konkuk.artium.data.repository.CommunityRepository
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val service: CommunityService
) : CommunityRepository {

    override suspend fun write(dto: CommunityPostRequestDto): CommunityDetailResponseDto {
        val response = service.write(dto)
        return response.data ?: throw Exception("작성 실패")
    }

    override suspend fun getList(): List<CommunityItemDto> {
        val response = service.getList()
        return response.data?.list ?: emptyList()
    }
    override suspend fun getDetail(id: Int): CommunityDetailResponseDto {
        return service.getPostDetail(id).data ?: throw Exception("No data")
    }

    override suspend fun update(id: Int, dto: CommunityPostRequestDto): CommunityDetailResponseDto {
        val response = service.update(id, dto)
        return response.data ?: throw Exception("수정 실패")
    }

    override suspend fun delete(id: Int) {
        service.delete(id)
    }

    override suspend fun writeComment(id: Int, content: String): CommentResponseDto {
        val response = service.writeComment(
            id,
            CommentWriteRequestDto(content)
        )
        return response.data ?: throw Exception("댓글 작성 실패")
    }

    override suspend fun deleteComment(commentId: Int) {
        service.deleteComment(commentId)
    }
}
