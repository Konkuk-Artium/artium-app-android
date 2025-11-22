package com.konkuk.artium.data.api

import com.konkuk.artium.data.dto.request.ArchiveRequestDto
import com.konkuk.artium.data.dto.response.ArchiveDetailResponseDto
import com.konkuk.artium.data.dto.response.ArchiveListResponse
import com.konkuk.artium.data.dto.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArchiveService {

    // 목록 조회
    @GET("diary")
    suspend fun getArchiveList(): BaseResponse<ArchiveListResponse>

    // 상세 조회 (ItemDto로 받아야 함!)
    @GET("diary/{id}")
    suspend fun getArchiveDetail(
        @Path("id") id: Int
    ): BaseResponse<ArchiveDetailResponseDto>

    // 글 쓰기 (ItemDto로 받아야 함!)
    @POST("diary")
    suspend fun postArchive(
        @Body body: ArchiveRequestDto
    ): BaseResponse<ArchiveDetailResponseDto>
}