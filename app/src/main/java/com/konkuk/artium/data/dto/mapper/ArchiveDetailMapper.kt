package com.konkuk.artium.data.dto.mapper

import com.konkuk.artium.data.dto.response.ArchiveDetailResponseDto
import com.konkuk.artium.ui.feature.archive.viewmodel.MyArtworkDetailUiState

/**
 * ArchiveDetailResponseDto → MyArtworkDetailUiState 변환 함수
 */
fun ArchiveDetailResponseDto.toUiState(): MyArtworkDetailUiState {
    return MyArtworkDetailUiState(
        id = product_id ?: 0L,
        title = title.orEmpty(),
        content = content.orEmpty(),
        viewDate = view_date.orEmpty(),
        rating = rating?.toFloat() ?: 0f,
        images = images?.map { it.img_url.orEmpty() } ?: emptyList(),
        isLoading = false,
        errorMessage = null
    )
}
