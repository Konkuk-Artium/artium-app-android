package com.konkuk.artium.data.dto.mapper

import com.konkuk.artium.data.dto.response.ArchiveResponseDto
import com.konkuk.artium.ui.feature.archive.component.RecentWorkItem
import com.konkuk.artium.ui.feature.archive.viewmodel.MyWorkItem

/**
 * ArchiveResponseDto → RecentWorkItem 변환
 */
fun ArchiveResponseDto.toRecentWorkItem(): RecentWorkItem {
    return RecentWorkItem(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rating = this.rating
    )
}

/**
 * ArchiveResponseDto → MyWorkItem 변환
 */
fun ArchiveResponseDto.toMyWorkItem(): MyWorkItem {
    return MyWorkItem(
        id = this.id,
        title = this.title,
        thumbnailUrl = this.imageUrl,
        date = "",     // TODO: 서버 DTO에 날짜가 생기면 변경
        rating = this.rating
    )
}
