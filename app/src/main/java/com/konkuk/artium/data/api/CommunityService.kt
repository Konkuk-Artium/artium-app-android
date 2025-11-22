package com.konkuk.artium.data.api

import com.konkuk.artium.data.dto.request.CommentWriteRequestDto
import com.konkuk.artium.data.dto.request.CommunityPostRequestDto
import com.konkuk.artium.data.dto.response.BaseResponse
import com.konkuk.artium.data.dto.response.CommentResponseDto
import com.konkuk.artium.data.dto.response.CommunityDetailResponseDto
import com.konkuk.artium.data.dto.response.CommunityListResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CommunityService {

    @POST("community")
    suspend fun write(
        @Body body: CommunityPostRequestDto
    ): BaseResponse<CommunityDetailResponseDto>

    @GET("community")
    suspend fun getList(): BaseResponse<CommunityListResponseDto>

    @GET("community/{id}")
    suspend fun getPostDetail(
        @Path("id") id: Int
    ): BaseResponse<CommunityDetailResponseDto>

    @PATCH("community/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body body: CommunityPostRequestDto
    ): BaseResponse<CommunityDetailResponseDto>

    @DELETE("community/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): BaseResponse<Unit>

    @POST("community/{id}/comment")
    suspend fun writeComment(
        @Path("id") id: Int,
        @Body body: CommentWriteRequestDto
    ): BaseResponse<CommentResponseDto>

    @DELETE("community/comment/{commentId}")
    suspend fun deleteComment(
        @Path("commentId") commentId: Int
    ): BaseResponse<Unit>
}
