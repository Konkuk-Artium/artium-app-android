package com.konkuk.artium.data.dto.response

import com.google.gson.annotations.SerializedName

// [1] 화면에서 쓸 데이터 (UI Model)
data class ArchiveResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val imageUrl: String,
    val rating: Float
)

// [2] 서버에서 오는 중간 포장지 (List Wrapper)
data class ArchiveListResponse(
    @SerializedName("list") val list: List<ArchiveItemDto>?,
    @SerializedName("total") val total: Int?
)

// [3] 서버에서 오는 진짜 알맹이 (Server DTO)
data class ArchiveItemDto(
    @SerializedName("product_id") val id: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("rating") val rating: Float?,
    @SerializedName("images") val images: List<ImageDto>?
)

// [4] 이미지 객체
data class ImageDto(
    @SerializedName("img_url") val imgUrl: String?
)

// [5] 변환 함수 (Mapper) - 이걸 쓰면 코드가 깔끔해짐!
fun ArchiveItemDto.toUiModel(): ArchiveResponseDto {
    return ArchiveResponseDto(
        id = this.id ?: 0L,
        title = this.title ?: "제목 없음",
        content = this.content ?: "",
        imageUrl = this.images?.firstOrNull()?.imgUrl ?: "",
        rating = this.rating ?: 0f
    )
}