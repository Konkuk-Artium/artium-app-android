package com.konkuk.artium.data.dto.response

import com.google.gson.annotations.SerializedName

data class ArchiveDetailResponseDto(
    @SerializedName("product_id") val product_id: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("view_date") val view_date: String?,
    @SerializedName("rating") val rating: Int?,
    @SerializedName("images") val images: List<ArchiveImageDto>?
)

data class ArchiveImageDto(
    @SerializedName("img_url") val img_url: String?,
    @SerializedName("img_order") val img_order: Int?
)
